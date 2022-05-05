package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.dto.CalculateConsumptionsPercentageResponse;
import com.isladellago.billmanager.domain.dto.GetConsumptionResponseDTO;
import com.isladellago.billmanager.domain.model.Apartment;
import com.isladellago.billmanager.domain.model.Consumption;
import com.isladellago.billmanager.domain.model.ConsumptionRepository;
import com.isladellago.billmanager.exception.*;
import com.isladellago.billmanager.service.ApartmentService;
import com.isladellago.billmanager.service.BillService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConsumptionServiceImplTest {

    @Mock
    private ConsumptionRepository consumptionRepository;

    @Mock
    private BillService billService;

    @Mock
    private ApartmentService apartmentService;

    @InjectMocks
    private ConsumptionServiceImpl consumptionService;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public final void testCreateConsumption() {
        Mockito.when(consumptionRepository
                .existsByBillBillIdAndApartmentApartmentId(
                        TestUtils.BILL_ID_1, TestUtils.APARTMENT_ID_201
                )).thenReturn(false);

        Mockito.when(apartmentService.getApartmentById(TestUtils.APARTMENT_ID_201, TestUtils.AUTH_UUID))
                .thenReturn(Apartment.builder().build());

        Mockito.when(billService.getBillById(TestUtils.BILL_ID_1, TestUtils.AUTH_UUID))
                .thenReturn(TestUtils.getBill_1());

        Mockito.when(consumptionRepository.save(Mockito.any()))
                .thenReturn(TestUtils.getConsumption_1());

        final Integer consumptionId = consumptionService
                .createConsumption(
                        TestUtils.getCreateConsumptionBodyDto_1(),
                        TestUtils.AUTH_UUID
                );

        Assert.assertNotNull(consumptionId);
        Assert.assertEquals(TestUtils.CONSUMPTION_ID_1, consumptionId);
    }

    @Test(expected = ConsumptionExistsWithBillIdAndApartmentId.class)
    public final void testConsumptionExistsWithBillIdAndApartmentId() {
        Mockito.when(consumptionRepository
                .existsByBillBillIdAndApartmentApartmentId(
                        TestUtils.BILL_ID_1, TestUtils.APARTMENT_ID_201
                )).thenReturn(true);

        consumptionService
                .createConsumption(TestUtils.getCreateConsumptionBodyDto_1(), TestUtils.AUTH_UUID);
    }

    @Test(expected = ApartmentNotFoundException.class)
    public final void testApartmentDoesNotExists() {
        Mockito.when(consumptionRepository
                .existsByBillBillIdAndApartmentApartmentId(
                        TestUtils.BILL_ID_1, TestUtils.APARTMENT_ID_201
                )).thenReturn(false);

        Mockito.when(apartmentService.getApartmentById(TestUtils.APARTMENT_ID_201, TestUtils.AUTH_UUID))
                .thenThrow(new ApartmentNotFoundException(TestUtils.APARTMENT_ID_201));

        consumptionService
                .createConsumption(TestUtils.getCreateConsumptionBodyDto_1(), TestUtils.AUTH_UUID);
    }

    @Test(expected = BillNotFoundException.class)
    public final void testBillDoesNotExists() {
        Mockito.when(consumptionRepository
                .existsByBillBillIdAndApartmentApartmentId(
                        TestUtils.BILL_ID_1, TestUtils.APARTMENT_ID_201
                )).thenReturn(false);

        Mockito.when(apartmentService.getApartmentById(TestUtils.APARTMENT_ID_201, TestUtils.AUTH_UUID))
                .thenReturn(Apartment.builder().build());

        Mockito.when(billService.getBillById(TestUtils.BILL_ID_1, TestUtils.AUTH_UUID))
                .thenThrow(new BillNotFoundException(TestUtils.BILL_ID_1));

        consumptionService
                .createConsumption(TestUtils.getCreateConsumptionBodyDto_1(), TestUtils.AUTH_UUID);
    }

    @Test
    public final void testGetConsumptionById() {
        final Consumption mockConsumption = TestUtils.getConsumption_1();

        Mockito.when(consumptionRepository.findById(TestUtils.BILL_ID_1))
                .thenReturn(Optional.of(mockConsumption));

        final Consumption consumption =
                consumptionService.getConsumptionById(TestUtils.CONSUMPTION_ID_1, TestUtils.AUTH_UUID);

        Assert.assertNotNull(consumption);
        Assert.assertEquals(mockConsumption, consumption);
    }

    @Test(expected = ConsumptionNotFoundException.class)
    public final void testGetConsumptionByIdDoesNotExists() {
        Mockito.when(consumptionRepository.findById(TestUtils.CONSUMPTION_ID_1))
                .thenReturn(Optional.empty());

        consumptionService
                .getConsumptionById(TestUtils.CONSUMPTION_ID_1, TestUtils.AUTH_UUID);
    }

    @Test
    public final void testMapGetConsumption() {
        final Consumption mockConsumption = TestUtils.getConsumption_1();
        final GetConsumptionResponseDTO response = consumptionService.mapGetConsumption(mockConsumption);

        Assert.assertNotNull(response);
        Assert.assertEquals(mockConsumption.getConsumptionId(), response.getConsumptionId());
        Assert.assertEquals(mockConsumption.getApartment().getApartmentId(), response.getApartmentId());
        Assert.assertEquals(mockConsumption.getBill().getBillId(), response.getBillId());
        Assert.assertEquals(
                mockConsumption.getResidentialBasicCubicMeters(),
                response.getResidentialBasicCubicMeters()
        );
        Assert.assertEquals(
                mockConsumption.getResidentialBasicSuperiorCubicMeters(),
                response.getResidentialBasicSuperiorCubicMeters()
        );
        Assert.assertEquals(mockConsumption.getValue(), response.getValue());
    }

    @Test
    public final void testCalculateConsumptionsPercentage() {
        Mockito.when(billService.getBillById(TestUtils.BILL_ID_1, TestUtils.AUTH_UUID))
                .thenReturn(TestUtils.getBill_1());

        Mockito.when(apartmentService.getApartmentById(Mockito.any(), Mockito.any()))
                .thenReturn(Apartment.builder().build());

        Mockito.when(consumptionRepository.save(Mockito.any()))
                .thenReturn(TestUtils.getConsumption_1());

        final CalculateConsumptionsPercentageResponse response = consumptionService
                .calculateConsumptionsPercentage(
                        TestUtils.getCalculateConsumptionsPercentageBody_1(),
                        TestUtils.AUTH_UUID
                );

        Assert.assertNotNull(response);
    }

    @Test(expected = ConsumptionsAlreadyCalculatedException.class)
    public final void testCalculateConsumptionsPercentageFails() {
        Mockito.when(consumptionRepository.findByBillBillId(TestUtils.BILL_ID_1))
                .thenReturn(Arrays.asList(new Consumption[10]));

        consumptionService.calculateConsumptionsPercentage(
                TestUtils.getCalculateConsumptionsPercentageBody_1(),
                TestUtils.AUTH_UUID
        );
    }

    @Test
    public final void testGetConsumptionsByBillId() {
        Mockito.when(consumptionRepository.findByBillBillId(TestUtils.BILL_ID_1))
                .thenReturn(List.of(TestUtils.getConsumption_1()));

        final List<Consumption> consumptions =
                consumptionService.getConsumptionsByBillId(TestUtils.BILL_ID_1, TestUtils.AUTH_UUID);

        Assert.assertNotNull(consumptions);
        Assert.assertEquals(1, consumptions.size());
    }
}
