package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public final class BillNotFoundException extends RuntimeException {

    private Integer billId;
    private LocalDate startDate;
    private LocalDate endDate;

    public BillNotFoundException(Integer billId) {
        this.billId = billId;
    }

    public BillNotFoundException(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
