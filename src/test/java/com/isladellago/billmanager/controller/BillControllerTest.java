package com.isladellago.billmanager.controller;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.dto.CreateBillBodyDTO;
import com.isladellago.billmanager.domain.dto.CreateBillResponseDTO;
import com.isladellago.billmanager.domain.dto.GetBillResponseDTO;
import com.isladellago.billmanager.service.BillService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BillControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public final void createBillTest() {
        final CreateBillBodyDTO createBillBodyDTO = TestUtils.getCreateBillPayload_1();

        Mockito.when(billService.createBill(TestUtils.AUTH_UUID, createBillBodyDTO))
                .thenReturn(TestUtils.BILL_ID_1);

        final ResponseEntity<CreateBillResponseDTO> response =
                billController.createBill(
                        TestUtils.AUTH_UUID,
                        TestUtils.AUTH_TOKEN,
                        createBillBodyDTO
                );

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(TestUtils.BILL_ID_1, (Integer) response.getBody().getBillId());
    }

    @Test
    public final void testGetBillById() {
        Mockito.when(billService.getBillById(TestUtils.BILL_ID_1, TestUtils.AUTH_UUID))
                .thenReturn(TestUtils.getBill_1());
        Mockito.when(billService.mapGetBill(Mockito.any()))
                .thenReturn(GetBillResponseDTO.builder().build());


        final ResponseEntity<GetBillResponseDTO> response =
                billController.getBillById(TestUtils.AUTH_UUID, TestUtils.AUTH_TOKEN, TestUtils.BILL_ID_1);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }
}
