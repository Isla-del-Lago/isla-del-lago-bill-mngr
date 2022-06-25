package com.isladellago.billmanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Maps delete bill response
 */
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public final class DeleteBillResponse {

    private Integer billId;
}
