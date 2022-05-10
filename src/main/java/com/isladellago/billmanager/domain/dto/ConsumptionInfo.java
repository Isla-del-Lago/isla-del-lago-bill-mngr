package com.isladellago.billmanager.domain.dto;

import com.isladellago.billmanager.util.ConstantUtils;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public final class ConsumptionInfo {

    @NotNull
    @Pattern(regexp = ConstantUtils.APARTMENT_REGEX)
    private final String apartmentId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private final Double value;
}
