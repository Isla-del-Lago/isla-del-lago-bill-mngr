package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.domain.model.Apartment;
import com.isladellago.billmanager.domain.model.ApartmentRepository;
import com.isladellago.billmanager.exception.ApartmentNotFoundException;
import com.isladellago.billmanager.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Override
    public Apartment getApartmentById(String apartmentId, UUID uuid) {
        log.info("[Get apartment by id service] Apartment id: {}, uuid: {}",
                apartmentId, uuid);

        return apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
    }
}
