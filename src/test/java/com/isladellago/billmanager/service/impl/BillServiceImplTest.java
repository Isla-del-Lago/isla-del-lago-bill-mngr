package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.model.BillRepository;
import com.isladellago.billmanager.exception.BillExistsWithDateRangeException;
import com.isladellago.billmanager.exception.InvalidBillDateRangeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BillServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public final void testCreateBill() {
        Mockito.when(billRepository.save(Mockito.any()))
                .thenReturn(TestUtils.getBill_1());

        final int billId =
                billService.createBill(TestUtils.AUTH_UUID, TestUtils.getCreateBillPayload_1());

        Assert.assertEquals(TestUtils.BILL_ID_1, (Integer) billId);
    }

    @Test(expected = InvalidBillDateRangeException.class)
    public final void testCreateBillStartDateIsAfterEndDate() {
        billService.createBill(TestUtils.AUTH_UUID, TestUtils.getCreateBillPayload_2());
    }

    @Test(expected = InvalidBillDateRangeException.class)
    public final void testCreateBillStartDateIsEqualToEndDate() {
        billService.createBill(TestUtils.AUTH_UUID, TestUtils.getCreateBillPayload_3());
    }

    @Test(expected = BillExistsWithDateRangeException.class)
    public final void testCreateBillAnotherBillExistsWithTheGivenStartDate() {
        Mockito.when(billRepository.existsByStartDate(TestUtils.START_DATE_1))
                .thenReturn(true);

        billService.createBill(TestUtils.AUTH_UUID, TestUtils.getCreateBillPayload_1());
    }

    @Test(expected = BillExistsWithDateRangeException.class)
    public final void testCreateBillAnotherBillExistsWithTheGivenEndDate() {
        Mockito.when(billRepository.existsByEndDate(TestUtils.END_DATE_1))
                .thenReturn(true);

        billService.createBill(TestUtils.AUTH_UUID, TestUtils.getCreateBillPayload_1());
    }
}
