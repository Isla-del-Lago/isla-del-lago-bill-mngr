package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public final class ConsumptionInfo {

    @NotNull
    private String apartmentId;

    @NotNull
    private Double value;
}
