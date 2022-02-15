package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class CalculateConsumptionsPercentageResponse {

    private List<ConsumptionResponse> consumptionIds;
}

