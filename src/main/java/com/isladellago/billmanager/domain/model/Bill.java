package com.isladellago.billmanager.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

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
    private Date creationDate = new Date();
}
