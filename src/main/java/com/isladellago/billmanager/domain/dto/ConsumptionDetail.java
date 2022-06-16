package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ConsumptionDetail {

    private final Double residentialBasicCubicMeters;
    private final Double residentialBasicSuperiorCubicMeters;
    private final Double discounts;
    private final Double residentialFixedAqueduct;
    private final Double residentialBasicAqueduct;
    private final Double residentialBasicSuperiorAqueduct;
    private final Double residentialFixedSewerage;
    private final Double residentialBasicSewerage;
    private final Double residentialBasicSuperiorSewerage;
    private final Double cleaning;
    private Double total;

    public void calculateTotal() {
        total = residentialFixedAqueduct + residentialBasicAqueduct + residentialBasicSuperiorAqueduct +
                residentialFixedSewerage + residentialBasicSewerage + residentialBasicSuperiorSewerage +
                cleaning - discounts;

        if (total < 0) total = 0d;
    }
}
