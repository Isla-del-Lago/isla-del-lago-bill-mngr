package com.isladellago.billmanager.exception;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.dto.ErrorResponseDTO;
import com.isladellago.billmanager.domain.enums.ErrorCodeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public final void testHandleInvalidBillDateRange() {
        final InvalidBillDateRangeException ex = InvalidBillDateRangeException.builder()
                .startDate(TestUtils.START_DATE_1)
                .endDate(TestUtils.END_DATE_1)
                .uuid(TestUtils.AUTH_UUID)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleInvalidBillDateRange(ex);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L300.getErrorCode(), response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L300.getErrorMessage(), response.getBody().getError()
        );
    }

    @Test
    public final void testHandleInvalidArgument() {
        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleInvalidArgument();

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L002.getErrorCode(), response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L002.getErrorMessage(), response.getBody().getError()
        );
    }

    @Test
    public final void testHandleBillExistsWithDateRange() {
        final BillExistsWithDateRangeException ex = BillExistsWithDateRangeException.builder()
                .startDate(TestUtils.START_DATE_1)
                .endDate(TestUtils.END_DATE_1)
                .uuid(TestUtils.AUTH_UUID)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleBillExistsWithDateRange(ex);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L301.getErrorCode(), response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L301.getErrorMessage(), response.getBody().getError()
        );
    }

    @Test
    public final void testHandleBillNotFound() {
        final BillNotFoundException ex = BillNotFoundException.builder()
                .billId(TestUtils.BILL_ID_1)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleBillNotFound(ex);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L302.getErrorCode(),
                response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L302.getErrorMessage(),
                response.getBody().getError()
        );
    }
}
