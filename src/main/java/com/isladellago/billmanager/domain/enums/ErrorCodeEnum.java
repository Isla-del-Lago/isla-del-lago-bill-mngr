package com.isladellago.billmanager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    L300("L-300", "Invalid start date and end date range");

    private final String errorCode;
    private final String errorMessage;
}
