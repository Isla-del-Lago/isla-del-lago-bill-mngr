package com.isladellago.billmanager.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {

    boolean existsByBillBillIdAndApartmentApartmentId(Integer billId, String apartmentId);
}
