package com.isladellago.billmanager.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public final class CreateBillBodyDTO {

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Positive
    private Integer residentialBasicCubicMeters;

    @NotNull
    @PositiveOrZero
    private Integer residentialBasicSuperiorCubicMeters;

    @NotNull
    @PositiveOrZero
    private Integer discounts;

    @NotNull
    @Positive
    private Double residentialFixedAqueduct;

    @NotNull
    @Positive
    private Double residentialBasicAqueduct;

    @NotNull
    @PositiveOrZero
    private Double residentialBasicSuperiorAqueduct;

    @NotNull
    @Positive
    private Double residentialFixedSewerage;

    @NotNull
    @Positive
    private Double residentialBasicSewerage;

    @NotNull
    @PositiveOrZero
    private Double residentialBasicSuperiorSewerage;

    @NotNull
    @PositiveOrZero
    private Integer cleaning;
}
