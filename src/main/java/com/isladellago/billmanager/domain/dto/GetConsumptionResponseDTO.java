package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public final class GetConsumptionResponseDTO {

    private Integer consumptionId;
    private Integer value;
    private Double residentialBasicCubicMeters;
    private Double residentialBasicSuperiorCubicMeters;
    private String apartmentId;
    private Integer billId;
}
