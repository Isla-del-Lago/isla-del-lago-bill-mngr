package com.isladellago.billmanager.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetBillResponseDTO {

    private Integer billId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
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
