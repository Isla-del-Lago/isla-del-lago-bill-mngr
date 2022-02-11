package com.isladellago.billmanager.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apartment")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public final class Apartment {

    @Id
    @Column(name = "apartment_id")
    private String apartmentId;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "apartment")
    private List<User> users;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartment")
    private List<Consumption> consumptions;
}
