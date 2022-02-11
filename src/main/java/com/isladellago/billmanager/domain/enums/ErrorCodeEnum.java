package com.isladellago.billmanager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    L300("L-300", "Invalid start date and end date range"),
    L002("L-002", "Invalid payload body, please review the values"),
    L301("L-301", "There is a bill with the given start or end date"),
    L302("L-302", "Bill not found");

    private final String errorCode;
    private final String errorMessage;
}
