package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.dto.GetBillResponseDTO;
import com.isladellago.billmanager.domain.model.Bill;
import com.isladellago.billmanager.domain.model.BillRepository;
import com.isladellago.billmanager.exception.BillExistsWithDateRangeException;
import com.isladellago.billmanager.exception.BillNotFoundException;
import com.isladellago.billmanager.exception.InvalidBillDateRangeException;
import com.isladellago.billmanager.service.BillService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Override
    public int createBill(UUID uuid, CreateBillBodyDTO createBillBodyDTO) {
        log.info("[Create bill service] uuid: {}, body dto: {}",
                uuid, createBillBodyDTO);

        final LocalDateTime startDate = createBillBodyDTO.getStartDate();
        final LocalDateTime endDate = createBillBodyDTO.getEndDate();

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
