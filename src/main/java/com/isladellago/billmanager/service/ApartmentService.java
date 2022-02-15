package com.isladellago.billmanager.service;

import com.isladellago.billmanager.domain.model.Apartment;

import java.util.UUID;

public interface ApartmentService {

    /**
     * Get the apartment by the given id.
     *
     * @param apartmentId Apartment id.
     * @param uuid        Execution uuid.
     * @return Apartment found.
     */
    Apartment getApartmentById(String apartmentId, UUID uuid);
}
