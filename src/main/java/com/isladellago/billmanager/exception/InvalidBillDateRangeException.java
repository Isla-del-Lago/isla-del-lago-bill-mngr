package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class InvalidBillDateRangeException extends RuntimeException {

    private final Date startDate;
    private final Date endDate;
    private final UUID uuid;
}
