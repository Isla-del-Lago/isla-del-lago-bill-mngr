package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsumptionNotFoundByBillIdAndApartmentId extends RuntimeException {

    private final Integer billId;
    private final String apartmentId;
}
