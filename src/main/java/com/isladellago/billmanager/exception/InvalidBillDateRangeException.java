package com.isladellago.billmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class InvalidBillDateRangeException extends RuntimeException {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final UUID uuid;
}
