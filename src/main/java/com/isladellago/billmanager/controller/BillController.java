package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.dto.CreateBillResponseDTO;
import com.isladellago.billmanager.domain.dto.DeleteBillResponse;
import com.isladellago.billmanager.domain.dto.GetBillResponseDTO;
import com.isladellago.billmanager.domain.model.Bill;
import com.isladellago.billmanager.service.BillService;
import com.isladellago.billmanager.util.CustomHttpHeaders;
import com.isladellago.billmanager.util.PathUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(PathUtils.BASE_PATH_BILL)
@AllArgsConstructor
@Log4j2
@CrossOrigin("http://localhost:3000/*")
public class BillController {

    private final BillService billService;

    @PostMapping(PathUtils.CREATE_BILL)
    public final ResponseEntity<CreateBillResponseDTO> createBill(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @Validated @RequestBody CreateBillBodyDTO createBillBodyDTO) {
        log.info("[Create bill controller] token: {}, uuid: {}, body: {}",
                authToken, uuid, createBillBodyDTO);

        final int billId = billService.createBill(uuid, createBillBodyDTO);

        final CreateBillResponseDTO response = CreateBillResponseDTO.builder()
                .billId(billId)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(PathUtils.BILL_ID)
    public final ResponseEntity<GetBillResponseDTO> getBillById(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @NotNull @PathVariable("bill-id") Integer billId) {
        log.info("[Get bill by id controller] Bill id: {}, uuid: {}, token: {}",
                billId, uuid, authToken);

        final Bill bill = billService.getBillById(billId, uuid);
        final GetBillResponseDTO bilLResponse = billService.mapGetBill(bill);

        log.info("[Get bill by id controller] Bill response: {}", bilLResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bilLResponse);
    }

    @GetMapping(PathUtils.GET_BILL_BY_START_AND_END_DATE)
    public final ResponseEntity<GetBillResponseDTO> getBillByStartAndEndDate(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @NotBlank @NotNull @RequestParam("startDate") String startDate,
            @NotBlank @NotNull @RequestParam("endDate") String endDate) {

        log.info("[Get bill by start and end date controller] Start date: {}, end date: {}, uuid: {}",
                startDate, endDate, uuid);

        final Bill bill = billService.getBillByStartAndEndDate(
                LocalDate.parse(startDate), LocalDate.parse(endDate), uuid
        );
        final GetBillResponseDTO response = billService.mapGetBill(bill);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(PathUtils.BILL_ID)
    public final ResponseEntity<DeleteBillResponse> deleteBillById(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @NotNull @Positive @PathVariable("bill-id") Integer billId) {

        log.info("[Delete bill by id controller] Bill id: {}, uuid: {}",
                billId, uuid);

        billService.deleteBillById(billId, uuid);

        final DeleteBillResponse response = DeleteBillResponse.builder()
                .billId(billId)
                .build();

        return ResponseEntity
                .accepted()
                .body(response);
    }
}
