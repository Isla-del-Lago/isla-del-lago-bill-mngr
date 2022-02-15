package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public final class CalculateConsumptionsPercentageBody {

    @NotNull
    private final Integer billId;

    @NotNull
    @NotEmpty
    private final List<ConsumptionInfo> consumptions;
}

