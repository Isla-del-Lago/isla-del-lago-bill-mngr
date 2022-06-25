package com.isladellago.billmanager.domain.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public final class GetConsumptionResponseDTO {

    private Integer consumptionId;
    private Double value;
    private Double residentialBasicCubicMeters;
    private Double residentialBasicSuperiorCubicMeters;
    private String apartmentId;
    private Integer billId;
}
