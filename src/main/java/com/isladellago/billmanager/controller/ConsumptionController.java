package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.domain.dto.*;
import com.isladellago.billmanager.domain.model.Consumption;
import com.isladellago.billmanager.service.ConsumptionService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathUtils.BASE_PATH_CONSUMPTION)
@AllArgsConstructor
@Log4j2
@CrossOrigin("*") //NOSONAR
public class ConsumptionController {

    private ConsumptionService consumptionService;

    @PostMapping(PathUtils.CREATE_CONSUMPTION)
    public final ResponseEntity<CreateConsumptionResponseDTO> createConsumption(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @RequestBody CreateConsumptionBodyDTO createConsumptionBodyDTO) {
        log.info("[Create consumption controller] Body: {}, uuid: {}, token: {}",
                uuid, authToken, createConsumptionBodyDTO);

        final Integer consumptionId =
                consumptionService.createConsumption(createConsumptionBodyDTO, uuid);

        final CreateConsumptionResponseDTO response = CreateConsumptionResponseDTO.builder()
                .consumptionId(consumptionId)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(PathUtils.GET_CONSUMPTION_BY_ID)
    public final ResponseEntity<GetConsumptionResponseDTO> getConsumptionById(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @NotNull @PathVariable("consumption-id") Integer consumptionId) {
        log.info("[Get consumption by id controller] Consumption id: {}, uuid: {}, token: {}",
                consumptionId, uuid, authToken);

        final Consumption consumption =
                consumptionService.getConsumptionById(consumptionId, uuid);

        final GetConsumptionResponseDTO response =
                consumptionService.mapGetConsumption(consumption);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping(PathUtils.CALCULATE_CONSUMPTIONS_PERCENTAGE)
    public final ResponseEntity<CalculateConsumptionsPercentageResponse> calculateConsumptionsPercentage(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @Validated @RequestBody CalculateConsumptionsPercentageBody body) {
        log.info("[Calculate consumptions percentage controller] Body: {}, uuid: {}, token: {}",
                body, uuid, authToken);

        final CalculateConsumptionsPercentageResponse responseBody =
                consumptionService.calculateConsumptionsPercentage(body, uuid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @GetMapping(PathUtils.GET_CONSUMPTION_DETAILS_BY_APARTMENT_AND_BILL)
    public final ResponseEntity<ConsumptionDetail> getConsumptionDetailByApartmentAndBill(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @PathVariable("apartment-id") String apartmentId,
            @PathVariable("bill-id") Integer billId) {
        log.info("[Get consumption detail by apartment and bill controller] " +
                "Apartment id: {}, bill id: {}, uuid: {}, authToken: {}", apartmentId, billId, uuid, authToken);

        final ConsumptionDetail consumptionDetail =
                consumptionService.getConsumptionDetailByApartmentIdAndBillId(apartmentId, billId, uuid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consumptionDetail);
    }

    @GetMapping(PathUtils.GET_ALL_CONSUMPTION_DETAILS_FROM_BILL_ID)
    public ResponseEntity<List<ConsumptionDetail>> getAllConsumptionDetailsFromBill(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @NotNull @NotBlank @PathVariable("bill-id") Integer billId) {
        log.info("[Get all consumptions from bill id controller] Bill id: {}, uuid: {}, authToken: {}",
                billId, uuid, authToken);

        return ResponseEntity
                .ok()
                .body(consumptionService.getAllConsumptionDetailsFromBillId(billId, uuid));

    }
}
