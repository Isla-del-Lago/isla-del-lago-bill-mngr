package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorResponseDTO {

    private String error;
    private String errorCode;
}
