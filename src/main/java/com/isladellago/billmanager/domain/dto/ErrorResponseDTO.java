package com.isladellago.billmanager.domain.dto;

import lombok.Builder;

@Builder
public final class ErrorResponseDTO {

    private final String error;
    private final String errorCode;
}
