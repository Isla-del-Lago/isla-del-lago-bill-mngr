package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.domain.dto.CreateConsumptionBodyDTO;
import com.isladellago.billmanager.domain.dto.CreateConsumptionResponseDTO;
import com.isladellago.billmanager.domain.dto.GetConsumptionResponseDTO;
import com.isladellago.billmanager.domain.model.Consumption;
import com.isladellago.billmanager.service.ConsumptionService;
import com.isladellago.billmanager.util.CustomHttpHeaders;
import com.isladellago.billmanager.util.PathUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
}
