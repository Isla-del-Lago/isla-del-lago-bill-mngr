package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public final class InvalidBillDateRangeException extends RuntimeException {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final UUID uuid;
}
