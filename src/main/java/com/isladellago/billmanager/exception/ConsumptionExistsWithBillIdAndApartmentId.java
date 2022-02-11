package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ConsumptionExistsWithBillIdAndApartmentId extends RuntimeException {

    private Integer billId;
    private String apartmentId;
}
