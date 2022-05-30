package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsumptionNotFoundByBillIdAndApartmentId extends RuntimeException {

    private final Integer billId;
    private final String apartmentId;
}
