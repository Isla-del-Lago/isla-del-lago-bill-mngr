package com.isladellago.billmanager.domain.dto;

import com.isladellago.billmanager.util.ConstantUtils;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public final class CreateConsumptionBodyDTO {

    @NotNull
    @Pattern(regexp = ConstantUtils.APARTMENT_REGEX)
    private final String apartmentId;

    @NotNull
    @Positive
    private final Integer billId;

    @NotNull
    @DecimalMin(value = ConstantUtils.MIN_CONSUMPTION_VALUE, inclusive = false)
    private final Double value;

    @NotNull
    @DecimalMin(value = ConstantUtils.MIN_CONSUMPTION_VALUE, inclusive = false)
    private final Double residentialBasicCubicMeters;

    @NotNull
    @DecimalMin(value = ConstantUtils.MIN_CONSUMPTION_VALUE, inclusive = false)
    private final Double residentialBasicSuperiorCubicMeters;
}
