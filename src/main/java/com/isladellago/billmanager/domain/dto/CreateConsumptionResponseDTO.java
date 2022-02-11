package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public final class CreateConsumptionResponseDTO {

    private Integer consumptionId;
}
