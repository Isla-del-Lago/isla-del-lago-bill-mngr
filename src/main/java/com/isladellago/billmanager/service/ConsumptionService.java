package com.isladellago.billmanager.service;

import com.isladellago.billmanager.domain.dto.CreateConsumptionBodyDTO;
import com.isladellago.billmanager.domain.dto.GetConsumptionResponseDTO;
import com.isladellago.billmanager.domain.model.Consumption;

import java.util.UUID;

public interface ConsumptionService {

    /**
     * Create a new consumption with the given data.
     *
     * @param createConsumptionBodyDTO Data to create the consumption.
     * @param uuid                     Execution uuid.
     * @return consumption created id.
     */
    Integer createConsumption(CreateConsumptionBodyDTO createConsumptionBodyDTO, UUID uuid);

    /**
     * Get the consumption by the given id.
     *
     * @param consumptionId Id to search the consumption.
     * @param uuid          Execution uuid.
     * @return Consumption.
     */
    Consumption getConsumptionById(Integer consumptionId, UUID uuid);

    /**
     * Maps the get consumption by id response from consumption
     * object.
     *
     * @param consumption Consumption to map the response.
     * @return Response mapped.
     */
    GetConsumptionResponseDTO mapGetConsumption(Consumption consumption);
}
