package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ConsumptionDetail {

    private Double residentialBasicCubicMeters;
    private Double residentialBasicSuperiorCubicMeters;
    private Double discounts;
    private Double residentialFixedAqueduct;
    private Double residentialBasicAqueduct;
    private Double residentialBasicSuperiorAqueduct;
    private Double residentialFixedSewerage;
    private Double residentialBasicSewerage;
    private Double residentialBasicSuperiorSewerage;
    private Double cleaning;
    private Double total;

    public void calculateTotal() {
        total = residentialFixedAqueduct + residentialBasicAqueduct + residentialBasicSuperiorAqueduct +
                residentialFixedSewerage + residentialBasicSewerage + residentialBasicSuperiorSewerage +
                cleaning - discounts;

        if (total < 0) total = 0d;
    }
}
