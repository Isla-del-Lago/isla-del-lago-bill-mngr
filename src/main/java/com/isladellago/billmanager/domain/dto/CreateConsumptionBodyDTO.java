package com.isladellago.billmanager.domain.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public final class CreateConsumptionBodyDTO {

    private final String apartmentId;
    private final Integer billId;
    private final Double value;
    private Double residentialBasicCubicMeters;
    private Double residentialBasicSuperiorCubicMeters;
}
