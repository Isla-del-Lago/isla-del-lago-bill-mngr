package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Maps delete bill response
 */
@AllArgsConstructor
@Getter
@Builder
public final class DeleteBillResponse {

    private final Integer billId;
}
