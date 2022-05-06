package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public final class CalculateConsumptionsPercentageBody {

    private static final int APARTMENTS_SIZE = 10;

    @NotNull
    @Positive
    private final Integer billId;

    @NotNull
    @NotEmpty
    @Size(min = APARTMENTS_SIZE, max = APARTMENTS_SIZE)
    private final List<@Valid ConsumptionInfo> consumptions;
}

