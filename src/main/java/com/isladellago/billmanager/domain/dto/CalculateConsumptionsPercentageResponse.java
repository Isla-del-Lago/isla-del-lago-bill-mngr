package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CalculateConsumptionsPercentageResponse {

    private List<ConsumptionResponse> consumptionIds;
}

