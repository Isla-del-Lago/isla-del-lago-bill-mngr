package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public final class CreateBillResponseDTO {

    private int billId;

}
