package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public final class ConsumptionNotFoundException extends RuntimeException {

    private Integer consumptionId;
    private String apartmentId;

    public ConsumptionNotFoundException(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public ConsumptionNotFoundException(String apartmentId) {
        this.apartmentId = apartmentId;
    }
}
