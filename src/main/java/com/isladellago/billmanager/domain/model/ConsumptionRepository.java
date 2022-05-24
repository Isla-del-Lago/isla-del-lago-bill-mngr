package com.isladellago.billmanager.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {

    boolean existsByBillBillIdAndApartmentApartmentId(Integer billId, String apartmentId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM consumption c " +
                    "JOIN bill b ON c.bill_id = b.bill_id " +
                    "WHERE b.start_date > ?1 AND c.apartment_id = ?2 " +
                    "ORDER BY b.start_date ASC")
    List<Consumption> findPreviousConsumption(LocalDateTime startDate, String apartmentId);

    Optional<Consumption> findByBillBillIdAndApartmentApartmentId(Integer previousBillId, String apartmentId);

    List<Consumption> findByBillBillId(Integer billId);

    List<Consumption> findAllByBillBillId(Integer billId);

    @Transactional
    void deleteAllByBillBillId(Integer billId);
}
