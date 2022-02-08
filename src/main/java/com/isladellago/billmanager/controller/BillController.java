package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.dto.CreateBillResponseDTO;
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

import java.util.UUID;

@RestController
@RequestMapping(PathUtils.BASE_PATH)
@CrossOrigin("*")
@AllArgsConstructor
@Log4j2
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
}
