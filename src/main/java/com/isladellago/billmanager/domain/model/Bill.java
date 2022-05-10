package com.isladellago.billmanager.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public final class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "residential_basic_cubic_meters")
    private Integer residentialBasicCubicMeters;

    @Column(name = "residential_basic_superior_cubic_meters")
    private Integer residentialBasicSuperiorCubicMeters;

    @Column
    private Integer discounts;

    @Column(name = "residential_fixed_aqueduct")
    private Double residentialFixedAqueduct;

    @Column(name = "residential_basic_aqueduct")
    private Double residentialBasicAqueduct;

    @Column(name = "residential_basic_superior_aqueduct")
    private Double residentialBasicSuperiorAqueduct;

    @Column(name = "residential_fixed_sewerage")
    private Double residentialFixedSewerage;

    @Column(name = "residential_basic_sewerage")
    private Double residentialBasicSewerage;

    @Column(name = "residential_basic_superior_sewerage")
    private Double residentialBasicSuperiorSewerage;

    @Column
    private Integer cleaning;

    @Column(name = "creation_date")
    private final LocalDateTime creationDate = LocalDateTime.now();

    @ToString.Exclude
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
    private List<Consumption> consumptions;
}
