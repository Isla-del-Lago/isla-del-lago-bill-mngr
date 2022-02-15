package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ConsumptionResponse {

    private String apartmentId;
    private Integer consumptionId;
}
