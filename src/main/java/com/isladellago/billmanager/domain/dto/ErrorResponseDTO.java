package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class ErrorResponseDTO {

    private final String error;
    private final String errorCode;
}
