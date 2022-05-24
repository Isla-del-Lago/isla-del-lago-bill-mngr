package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.dto.GetBillResponseDTO;
import com.isladellago.billmanager.domain.model.Bill;
import com.isladellago.billmanager.domain.model.BillRepository;
import com.isladellago.billmanager.domain.model.ConsumptionRepository;
import com.isladellago.billmanager.exception.BillExistsWithDateRangeException;
import com.isladellago.billmanager.exception.BillNotFoundException;
import com.isladellago.billmanager.exception.InvalidBillDateRangeException;
import com.isladellago.billmanager.service.BillService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ConsumptionRepository consumptionRepository;

    @Override
    public int createBill(UUID uuid, CreateBillBodyDTO createBillBodyDTO) {
        log.info("[Create bill service] uuid: {}, body dto: {}",
                uuid, createBillBodyDTO);

        final LocalDate startDate = createBillBodyDTO.getStartDate();
        final LocalDate endDate = createBillBodyDTO.getEndDate();

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new InvalidBillDateRangeException(startDate, endDate, uuid);
        }

        if (billRepository.existsByStartDate(startDate) ||
                billRepository.existsByEndDate(endDate)) {
            throw new BillExistsWithDateRangeException(startDate, endDate, uuid);
        }

        final Bill mappedBill = mapBillFromDto(createBillBodyDTO);

        log.info("[Create bill service] Bill to be saved: {}, uuid: {}",
                mappedBill, uuid);

        return billRepository
                .save(mappedBill)
                .getBillId();
    }

    @Override
    public Bill getBillById(Integer billId, UUID uuid) {
        log.info("[Get bill by id service] Bill id: {}, uuid: {}",
                billId, uuid);

        return billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException(billId));
    }

    @Override
    public GetBillResponseDTO mapGetBill(Bill bill) {
        return GetBillResponseDTO.builder()
                .billId(bill.getBillId())
                .startDate(bill.getStartDate())
                .endDate(bill.getEndDate())
                .residentialBasicCubicMeters(bill.getResidentialBasicCubicMeters())
                .residentialBasicSuperiorCubicMeters(bill.getResidentialBasicSuperiorCubicMeters())
                .discounts(bill.getDiscounts())
                .residentialFixedAqueduct(bill.getResidentialFixedAqueduct())
                .residentialBasicAqueduct(bill.getResidentialBasicAqueduct())
                .residentialBasicSuperiorAqueduct(bill.getResidentialBasicSuperiorAqueduct())
                .residentialFixedSewerage(bill.getResidentialFixedSewerage())
                .residentialBasicSewerage(bill.getResidentialBasicSewerage())
                .residentialBasicSuperiorSewerage(bill.getResidentialBasicSuperiorSewerage())
                .cleaning(bill.getCleaning())
                .build();
    }

    @Override
    public boolean existsById(Integer billId) {
        log.info("[Bill exists by id service] Bill id: {}", billId);
        return billRepository.existsById(billId);
    }

    @Override
    public Optional<Bill> getPreviousBill(Bill bill) {
        log.info("[Get previous bill service] Current bill: {}", bill);

        final List<Bill> previousBills =
                billRepository.findByStartDateBeforeOrderByStartDateDesc(bill.getStartDate());

        log.info("[Get previous bills] Previous bills: {}", previousBills);

        if (previousBills.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(previousBills.get(0));
    }

    @Override
    public Bill getBillByStartAndEndDate(
            LocalDate startDate, LocalDate endDate, UUID uuid) {

        log.info("[Get bill by start and end date service] Start date: {}, end date: {}, uuid: {}",
                startDate, endDate, uuid);

        return billRepository.findByStartDateAndEndDate(startDate, endDate)
                .orElseThrow(() -> new BillNotFoundException(startDate, endDate));
    }

    @Override
    public void deleteBillById(Integer billId, UUID uuid) {
        log.info("[Delete bill by id service] Bill id: {}, uuid: {}",
                billId, uuid);

        billRepository.deleteById(billId);
        consumptionRepository.deleteAllByBillBillId(billId);
    }

    /**
     * This method maps the creation bill dto with a new
     * bill entity.
     *
     * @param dto Dto to be mapped.
     * @return Bill mapped.
     */
    private Bill mapBillFromDto(CreateBillBodyDTO dto) {
        return Bill.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .residentialBasicCubicMeters(dto.getResidentialBasicCubicMeters())
                .residentialBasicSuperiorCubicMeters(dto.getResidentialBasicSuperiorCubicMeters())
                .discounts(dto.getDiscounts())
                .residentialFixedAqueduct(dto.getResidentialFixedAqueduct())
                .residentialBasicAqueduct(dto.getResidentialBasicAqueduct())
                .residentialBasicSuperiorAqueduct(dto.getResidentialBasicSuperiorAqueduct())
                .residentialFixedSewerage(dto.getResidentialFixedSewerage())
                .residentialBasicSewerage(dto.getResidentialBasicSewerage())
                .residentialBasicSuperiorSewerage(dto.getResidentialBasicSuperiorSewerage())
                .cleaning(dto.getCleaning())
                .build();
    }
}
