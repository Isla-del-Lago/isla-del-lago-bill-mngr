package com.isladellago.billmanager.service;

import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;

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
}
