package com.isladellago.billmanager.service;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.dto.GetBillResponseDTO;
import com.isladellago.billmanager.domain.model.Bill;

import java.util.Optional;
import java.util.UUID;

public interface BillService {

    /**
     * Create a new bill with the given data.
     *
     * @param uuid              Unique identifier.
     * @param createBillBodyDTO Given data to create the bill.
     * @return Created bill id.
     */
    int createBill(UUID uuid, CreateBillBodyDTO createBillBodyDTO);

    /**
     * Search a bill by the given bill id.
     *
     * @param billId Bill id to search the bill.
     * @param uuid   Request uuid.
     * @return Bill.
     */
    Bill getBillById(Integer billId, UUID uuid);

    /**
     * Maps the get bill response from bill object.
     *
     * @param bill Bill to map the get bill response.
     * @return Get bill response mapped.
     */
    GetBillResponseDTO mapGetBill(Bill bill);

    /**
     * Validates if a bill exists by the given id.
     *
     * @param billId Bill id to validate.
     * @return If bill exists or not.
     */
    boolean existsById(Integer billId);

    /**
     * Get the previous bill from the given bill.
     *
     * @param bill Bill object.
     * @return Previous bill if exists.
     */
    Optional<Bill> getPreviousBill(Bill bill);
}
