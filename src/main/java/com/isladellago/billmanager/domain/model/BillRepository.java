package com.isladellago.billmanager.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    boolean existsByStartDate(LocalDateTime startDate);

    boolean existsByEndDate(LocalDateTime endDate);

    List<Bill> findByStartDateBeforeOrderByStartDateDesc(LocalDateTime startDate);
}
