package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.domain.dto.*;
import com.isladellago.billmanager.domain.model.Apartment;
import com.isladellago.billmanager.domain.model.Bill;
import com.isladellago.billmanager.domain.model.Consumption;
import com.isladellago.billmanager.domain.model.ConsumptionRepository;
import com.isladellago.billmanager.exception.ConsumptionExistsWithBillIdAndApartmentId;
import com.isladellago.billmanager.exception.ConsumptionNotFoundByBillIdAndApartmentId;
import com.isladellago.billmanager.exception.ConsumptionNotFoundException;
import com.isladellago.billmanager.service.ApartmentService;
import com.isladellago.billmanager.service.BillService;
import com.isladellago.billmanager.service.ConsumptionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ApartmentService apartmentService;
    private final BillService billService;

    @Override
    public Integer createConsumption(CreateConsumptionBodyDTO createConsumptionBodyDTO, UUID uuid) {
        log.info("[Create consumption service] Body: {}, uuid: {}",
                createConsumptionBodyDTO, uuid);

        final Integer billId = createConsumptionBodyDTO.getBillId();
        final String apartmentId = createConsumptionBodyDTO.getApartmentId();

        if (consumptionRepository.existsByBillBillIdAndApartmentApartmentId(billId, apartmentId)) {
            throw new ConsumptionExistsWithBillIdAndApartmentId(billId, apartmentId);
        }

        final Consumption consumption =
                mapConsumptionFromCreateConsumptionDto(createConsumptionBodyDTO, uuid);

        return consumptionRepository
                .save(consumption)
                .getConsumptionId();
    }

    @Override
    public Consumption getConsumptionById(Integer consumptionId, UUID uuid) {
        log.info("[Get consumption by id] Consumption id: {}, uuid: {}",
                consumptionId, uuid);

        return consumptionRepository.findById(consumptionId)
                .orElseThrow(() -> new ConsumptionNotFoundException(consumptionId));
    }

    @Override
    public GetConsumptionResponseDTO mapGetConsumption(Consumption consumption) {
        return GetConsumptionResponseDTO.builder()
                .consumptionId(consumption.getConsumptionId())
                .value(consumption.getValue())
                .residentialBasicCubicMeters(consumption.getResidentialBasicCubicMeters())
                .residentialBasicSuperiorCubicMeters(consumption.getResidentialBasicSuperiorCubicMeters())
                .apartmentId(consumption.getApartment().getApartmentId())
                .billId(consumption.getBill().getBillId())
                .build();
    }

    @Override
    public CalculateConsumptionsPercentageResponse calculateConsumptionsPercentage(
            CalculateConsumptionsPercentageBody consumptionsInfo, UUID uuid) {
        log.info("[Calculate consumptions percentage service] Consumptions info: {}, uuid: {}",
                consumptionsInfo, uuid);

        final Bill bill = billService.getBillById(consumptionsInfo.getBillId(), uuid);
        final Map<String, Double> calculatedConsumptionValues = calculateConsumptionValues(consumptionsInfo, bill);

        final List<ConsumptionResponse> consumptionResponses = consumptionsInfo
                .getConsumptions()
                .stream()
                .map(mapConsumptionResponse(uuid, bill, calculatedConsumptionValues))
                .collect(Collectors.toList());

        return CalculateConsumptionsPercentageResponse.builder()
                .consumptionIds(consumptionResponses)
                .build();
    }

    @Override
    public ConsumptionDetail getConsumptionDetailByApartmentIdAndBillId(String apartmentId,
                                                                        Integer billId, UUID uuid) {
        log.info("[Get consumption detail by apartment and bill controller] " +
                "Apartment id: {}, billId: {}, uuid: {}", apartmentId, billId, uuid);

        final Consumption consumption =
                findConsumptionByApartmentIdAndBillId(apartmentId, billId);
        final Bill bill = billService.getBillById(billId, uuid);

        final Double residentialBasicCubicMeters =
                consumption.getResidentialBasicCubicMeters();
        final Double residentialBasicSuperiorCubicMeters =
                consumption.getResidentialBasicSuperiorCubicMeters();

        return ConsumptionDetail.builder()
                .residentialBasicCubicMeters(residentialBasicCubicMeters)
                .residentialBasicSuperiorCubicMeters(residentialBasicSuperiorCubicMeters)
                .discounts((double) bill.getDiscounts() / 10)
                .residentialFixedAqueduct(bill.getResidentialFixedAqueduct() / 10)
                .residentialBasicAqueduct(bill.getResidentialBasicAqueduct() * residentialBasicCubicMeters)
                .residentialBasicSuperiorAqueduct(
                        bill.getResidentialBasicSuperiorAqueduct() * residentialBasicSuperiorCubicMeters
                )
                .residentialFixedSewerage(bill.getResidentialFixedSewerage() / 10)
                .residentialBasicSewerage(bill.getResidentialBasicSewerage() * residentialBasicCubicMeters)
                .residentialBasicSuperiorSewerage(
                        bill.getResidentialBasicSuperiorSewerage() * residentialBasicSuperiorCubicMeters
                )
                .cleaning((double) bill.getCleaning() / 10)
                .build();
    }

    /**
     * Calculate the subtraction between the current consumption
     * value and the previous.
     *
     * @param consumptionsInfo Current consumptions info.
     * @param bill             Current bill.
     * @return Consumptions calculed.
     */
    private Map<String, Double> calculateConsumptionValues(
            CalculateConsumptionsPercentageBody consumptionsInfo, Bill bill) {

        final Optional<Bill> previousBill = billService.getPreviousBill(bill);
        final Map<String, Double> valuesMap = new HashMap<>();

        consumptionsInfo.getConsumptions().forEach(consumptionInfo -> {
            final Double previousConsumptionValue =
                    getPreviousConsumptionValue(previousBill, consumptionInfo.getApartmentId());

            log.info("Current consumption value: {}, previous value: {}",
                    consumptionInfo.getValue(), previousConsumptionValue);

            valuesMap.put(
                    consumptionInfo.getApartmentId(),
                    consumptionInfo.getValue() - previousConsumptionValue
            );
        });

        return valuesMap;
    }

    /**
     * Save a consumption and map consumption response.
     *
     * @param uuid                        Execution uuid.
     * @param bill                        Bill object.
     * @param calculatedConsumptionValues Calculated consumption values.
     * @return Function that saves a consumption and maps a consumption response.
     */
    private Function<ConsumptionInfo, ConsumptionResponse> mapConsumptionResponse(UUID uuid, Bill bill,
                                                                                  Map<String, Double> calculatedConsumptionValues) {
        final Double consumptionValuesTotal = calculatedConsumptionValues
                .values()
                .stream()
                .reduce(0d, Double::sum);

        log.info("Total consumption values: {}", consumptionValuesTotal);

        return consumptionInfo -> {
            final String apartmentId = consumptionInfo.getApartmentId();
            final Apartment apartment =
                    apartmentService.getApartmentById(apartmentId, uuid);

            final Double consumptionPercentage =
                    ((calculatedConsumptionValues.get(apartmentId) * 100) / consumptionValuesTotal) / 100;
            final Consumption consumption =
                    mapConsumption(bill, consumptionInfo, consumptionPercentage);
            consumption.setApartment(apartment);

            log.info("Consumption percentage: {}, consumption to be saved: {}",
                    consumptionPercentage, consumption);

            final Integer savedConsumptionId = consumptionRepository
                    .save(consumption)
                    .getConsumptionId();

            log.info("Saved consumption id: {}", savedConsumptionId);

            return ConsumptionResponse.builder()
                    .consumptionId(savedConsumptionId)
                    .apartmentId(consumptionInfo.getApartmentId())
                    .build();
        };
    }

    /**
     * Maps a new consumption from the given information.
     *
     * @param bill                  Bill object.
     * @param consumptionInfo       Consumption information.
     * @param consumptionPercentage Calculated consumption percentage.
     * @return Consumption mapped.
     */
    private Consumption mapConsumption(Bill bill, ConsumptionInfo consumptionInfo,
                                       Double consumptionPercentage) {
        final Double residentialBasicCubicMeters =
                bill.getResidentialBasicCubicMeters() * consumptionPercentage;
        final Double residentialBasicSuperiorCubicMeters =
                bill.getResidentialBasicSuperiorCubicMeters() * consumptionPercentage;

        return Consumption.builder()
                .bill(bill)
                .value(consumptionInfo.getValue())
                .residentialBasicCubicMeters(residentialBasicCubicMeters)
                .residentialBasicSuperiorCubicMeters(residentialBasicSuperiorCubicMeters)
                .build();
    }

    /**
     * Calculates the sum from every given consumption.
     *
     * @param consumptions Consumptions list.
     * @return Consumption values sum.
     */
    private Double calculateTotal(List<ConsumptionInfo> consumptions, Bill bill) {
        final Optional<Bill> previousBill = billService.getPreviousBill(bill);
        return consumptions
                .stream()
                .map(consumptionInfo -> getPreviousConsumptionValue(previousBill, consumptionInfo.getApartmentId()))
                .reduce(0d, Double::sum);
    }

    private Double getPreviousConsumptionValue(Optional<Bill> previousBill, String apartmentId) {
        if (previousBill.isEmpty()) {
            return 0d;
        }

        final Integer previousBillId = previousBill.get().getBillId();
        final Consumption previousConsumption =
                findConsumptionByApartmentIdAndBillId(apartmentId, previousBillId);

        log.info("Previous consumption: {}", previousConsumption);

        return previousConsumption.getValue();
    }

    private Consumption findConsumptionByApartmentIdAndBillId(String apartmentId, Integer billId) {
        return consumptionRepository.findByBillBillIdAndApartmentApartmentId(billId, apartmentId)
                .orElseThrow(() -> new ConsumptionNotFoundByBillIdAndApartmentId(billId, apartmentId));
    }

    /**
     * Maps a consumption from the given data on create
     * consumption body dto
     *
     * @param dto  Body dto.
     * @param uuid Execution uuid.
     * @return Consumption mapped.
     */
    private Consumption mapConsumptionFromCreateConsumptionDto(
            CreateConsumptionBodyDTO dto, UUID uuid) {

        final Apartment apartment =
                apartmentService.getApartmentById(dto.getApartmentId(), uuid);
        final Bill bill = billService.getBillById(dto.getBillId(), uuid);

        return Consumption.builder()
                .apartment(apartment)
                .bill(bill)
                .value(dto.getValue())
                .residentialBasicCubicMeters(dto.getResidentialBasicCubicMeters())
                .residentialBasicSuperiorCubicMeters(dto.getResidentialBasicSuperiorCubicMeters())
                .build();
    }
}
