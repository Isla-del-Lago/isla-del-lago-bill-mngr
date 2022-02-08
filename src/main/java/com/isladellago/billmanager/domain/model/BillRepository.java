package com.isladellago.billmanager.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    boolean existsByStartDate(LocalDateTime startDate);

    boolean existsByEndDate(LocalDateTime endDate);
}
