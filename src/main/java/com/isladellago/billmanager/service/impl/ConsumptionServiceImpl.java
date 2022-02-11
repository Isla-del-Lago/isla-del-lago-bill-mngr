package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.domain.dto.CreateConsumptionBodyDTO;
import com.isladellago.billmanager.domain.dto.GetConsumptionResponseDTO;
import com.isladellago.billmanager.domain.model.*;
import com.isladellago.billmanager.exception.ApartmentNotFoundException;
import com.isladellago.billmanager.exception.BillNotFoundException;
import com.isladellago.billmanager.exception.ConsumptionExistsWithBillIdAndApartmentId;
import com.isladellago.billmanager.exception.ConsumptionNotFoundException;
import com.isladellago.billmanager.service.ConsumptionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private ConsumptionRepository consumptionRepository;
    private ApartmentRepository apartmentRepository;
    private BillRepository billRepository;

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
                mapConsumptionFromCreateConsumptionDto(createConsumptionBodyDTO);

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

    /**
     * Maps a consumption from the given data on create
     * consumption body dto
     *
     * @param dto Body dto.
     * @return Consumption mapped.
     */
    private Consumption mapConsumptionFromCreateConsumptionDto(CreateConsumptionBodyDTO dto) {
        final Apartment apartment = apartmentRepository.findById(dto.getApartmentId())
                .orElseThrow(() -> new ApartmentNotFoundException(dto.getApartmentId()));

        final Bill bill = billRepository.findById(dto.getBillId())
                .orElseThrow(() -> new BillNotFoundException(dto.getBillId()));

        return Consumption.builder()
                .apartment(apartment)
                .bill(bill)
                .value(dto.getValue())
                .residentialBasicCubicMeters(dto.getResidentialBasicCubicMeters())
                .residentialBasicSuperiorCubicMeters(dto.getResidentialBasicSuperiorCubicMeters())
                .build();
    }
}
