package com.isladellago.billmanager.domain.dto;

import com.isladellago.billmanager.util.ConstantUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ConsumptionInfo {

    @NotNull
    @Pattern(regexp = ConstantUtils.APARTMENT_REGEX)
    private String apartmentId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double value;
}
