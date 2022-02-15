package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.dto.*;
import com.isladellago.billmanager.domain.model.Consumption;
import com.isladellago.billmanager.service.ConsumptionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ConsumptionControllerTest {

    @Mock
    private ConsumptionService consumptionService;

    @InjectMocks
    private ConsumptionController consumptionController;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public final void testCreateConsumption() {
        final CreateConsumptionBodyDTO mockDto =
                TestUtils.getCreateConsumptionBodyDto_1();

        Mockito.when(consumptionService.createConsumption(mockDto, TestUtils.AUTH_UUID))
                .thenReturn(TestUtils.CONSUMPTION_ID_1);

        final ResponseEntity<CreateConsumptionResponseDTO> response =
                consumptionController.createConsumption(
                        TestUtils.AUTH_UUID,
                        TestUtils.AUTH_TOKEN,
                        mockDto
                );

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(TestUtils.CONSUMPTION_ID_1, response.getBody().getConsumptionId());
    }

    @Test
    public final void testGetBillById() {
        final Consumption mockConsumption = TestUtils.getConsumption_1();

        Mockito.when(consumptionService.getConsumptionById(TestUtils.CONSUMPTION_ID_1, TestUtils.AUTH_UUID))
                .thenReturn(mockConsumption);

        Mockito.when(consumptionService.mapGetConsumption(mockConsumption))
                .thenReturn(GetConsumptionResponseDTO.builder().build());

        final ResponseEntity<GetConsumptionResponseDTO> response =
                consumptionController.getConsumptionById(
                        TestUtils.AUTH_UUID,
                        TestUtils.AUTH_TOKEN,
                        TestUtils.CONSUMPTION_ID_1
                );

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public final void testCalculateConsumptionsPercentage() {
        final CalculateConsumptionsPercentageBody body =
                TestUtils.getCalculateConsumptionsPercentageBody_1();

        Mockito.when(consumptionService.calculateConsumptionsPercentage(body, TestUtils.AUTH_UUID))
                .thenReturn(TestUtils.getCalculateConsumptionsPerecentageResponse());

        final ResponseEntity<CalculateConsumptionsPercentageResponse> response = consumptionController
                .calculateConsumptionsPercentage(TestUtils.AUTH_UUID, TestUtils.AUTH_TOKEN, body);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(2, response.getBody().getConsumptionIds().size());
    }
}
