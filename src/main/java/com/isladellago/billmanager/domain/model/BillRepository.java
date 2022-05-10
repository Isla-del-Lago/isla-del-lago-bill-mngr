package com.isladellago.billmanager.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    boolean existsByStartDate(LocalDate startDate);

    boolean existsByEndDate(LocalDate endDate);

    List<Bill> findByStartDateBeforeOrderByStartDateDesc(LocalDate startDate);

    Optional<Bill> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}
