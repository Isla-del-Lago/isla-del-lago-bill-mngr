package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.model.Apartment;
import com.isladellago.billmanager.domain.model.ApartmentRepository;
import com.isladellago.billmanager.domain.model.BillRepository;
import com.isladellago.billmanager.domain.model.ConsumptionRepository;
import com.isladellago.billmanager.exception.ApartmentNotFoundException;
import com.isladellago.billmanager.exception.BillNotFoundException;
import com.isladellago.billmanager.exception.ConsumptionExistsWithBillIdAndApartmentId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ConsumptionServiceImplTest {

    @Mock
    private ConsumptionRepository consumptionRepository;

    @Mock
    private BillRepository billRepository;

    @Mock
    private ApartmentRepository apartmentRepository;

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

        Mockito.when(apartmentRepository.findById(TestUtils.APARTMENT_ID_201))
                .thenReturn(Optional.of(Apartment.builder().build()));

        Mockito.when(billRepository.findById(TestUtils.BILL_ID_1))
                .thenReturn(Optional.of(TestUtils.getBill_1()));

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

        Mockito.when(apartmentRepository.findById(TestUtils.APARTMENT_ID_201))
                .thenReturn(Optional.empty());

        consumptionService
                .createConsumption(TestUtils.getCreateConsumptionBodyDto_1(), TestUtils.AUTH_UUID);
    }

    @Test(expected = BillNotFoundException.class)
    public final void testBillDoesNotExists() {
        Mockito.when(consumptionRepository
                .existsByBillBillIdAndApartmentApartmentId(
                        TestUtils.BILL_ID_1, TestUtils.APARTMENT_ID_201
                )).thenReturn(false);

        Mockito.when(apartmentRepository.findById(TestUtils.APARTMENT_ID_201))
                .thenReturn(Optional.of(Apartment.builder().build()));

        Mockito.when(billRepository.findById(TestUtils.BILL_ID_1))
                .thenReturn(Optional.empty());

        consumptionService
                .createConsumption(TestUtils.getCreateConsumptionBodyDto_1(), TestUtils.AUTH_UUID);
    }
}
