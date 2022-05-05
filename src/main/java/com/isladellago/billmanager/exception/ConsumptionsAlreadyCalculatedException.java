package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public final class ConsumptionsAlreadyCalculatedException extends RuntimeException {

    private Integer billId;
}
