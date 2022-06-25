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
@NoArgsConstructor
public final class CreateConsumptionBodyDTO {

    @NotNull
    @Pattern(regexp = ConstantUtils.APARTMENT_REGEX)
    private String apartmentId;

    @NotNull
    @Positive
    private Integer billId;

    @NotNull
    @DecimalMin(value = ConstantUtils.MIN_CONSUMPTION_VALUE, inclusive = false)
    private Double value;

    @NotNull
    @DecimalMin(value = ConstantUtils.MIN_CONSUMPTION_VALUE, inclusive = false)
    private Double residentialBasicCubicMeters;

    @NotNull
    @DecimalMin(value = ConstantUtils.MIN_CONSUMPTION_VALUE, inclusive = false)
    private Double residentialBasicSuperiorCubicMeters;
}
