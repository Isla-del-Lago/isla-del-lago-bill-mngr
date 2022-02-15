package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ConsumptionDetail {

    private final Double residentialBasicCubicMeters;
    private Double residentialBasicSuperiorCubicMeters;
    private Double discounts;
    private Double residentialFixedAqueduct;
    private Double residentialBasicAqueduct;
    private Double residentialBasicSuperiorAqueduct;
    private Double residentialFixedSewerage;
    private Double residentialBasicSewerage;
    private Double residentialBasicSuperiorSewerage;
    private Double cleaning;
}
