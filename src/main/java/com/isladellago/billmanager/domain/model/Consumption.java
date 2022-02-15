package com.isladellago.billmanager.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "consumption")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public final class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumption_id")
    private Integer consumptionId;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Column
    private Double value;

    @Column(name = "residential_basic_cubic_meters")
    private Double residentialBasicCubicMeters;

    @Column(name = "residential_basic_superior_cubic_meters")
    private Double residentialBasicSuperiorCubicMeters;
}
