package com.isladellago.billmanager.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
public class GetBillResponseDTO {

    private Integer billId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer residentialBasicCubicMeters;
    private Integer residentialBasicSuperiorCubicMeters;
    private Integer discounts;
    private Double residentialFixedAqueduct;
    private Double residentialBasicAqueduct;
    private Double residentialBasicSuperiorAqueduct;
    private Double residentialFixedSewerage;
    private Double residentialBasicSewerage;
    private Double residentialBasicSuperiorSewerage;
    private Integer cleaning;
}
