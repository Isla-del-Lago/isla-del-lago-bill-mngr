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

        testResponse(response, HttpStatus.CONFLICT, ErrorCodeEnum.L300);
    }

/*    @Test
    public final void testHandleInvalidArgument() {
        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleInvalidArgument(new MethodArgumentNotValidException());

        testResponse(response, HttpStatus.BAD_REQUEST, ErrorCodeEnum.L002);
    }*/

    @Test
    public final void testHandleBillExistsWithDateRange() {
        final BillExistsWithDateRangeException ex = BillExistsWithDateRangeException.builder()
                .startDate(TestUtils.START_DATE_1)
                .endDate(TestUtils.END_DATE_1)
                .uuid(TestUtils.AUTH_UUID)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleBillExistsWithDateRange(ex);

        testResponse(response, HttpStatus.CONFLICT, ErrorCodeEnum.L301);
    }

    @Test
    public final void testHandleBillNotFound() {
        final BillNotFoundException ex = BillNotFoundException.builder()
                .billId(TestUtils.BILL_ID_1)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleBillNotFound(ex);

        testResponse(response, HttpStatus.NOT_FOUND, ErrorCodeEnum.L302);
    }

    @Test
    public final void testHandleApartmentNotFoundException() {
        final ApartmentNotFoundException ex = ApartmentNotFoundException.builder()
                .apartmentId(TestUtils.APARTMENT_ID_201)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleApartmentNotFoundException(ex);

        testResponse(response, HttpStatus.NOT_FOUND, ErrorCodeEnum.L200);
    }

    @Test
    public final void testHandleConsumptionNotFoundException() {
        final ConsumptionNotFoundException ex = ConsumptionNotFoundException.builder()
                .consumptionId(TestUtils.CONSUMPTION_ID_1)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleConsumptionNotFoundException(ex);

        testResponse(response, HttpStatus.NOT_FOUND, ErrorCodeEnum.L400);
    }

    @Test
    public final void testConsumptionExistsWithBillIdAndApartmentId() {
        final ConsumptionExistsWithBillIdAndApartmentId ex = ConsumptionExistsWithBillIdAndApartmentId.builder()
                .apartmentId(TestUtils.APARTMENT_ID_201)
                .billId(TestUtils.BILL_ID_1)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleConsumptionExistsWithBillIdAndApartmentId(ex);

        testResponse(response, HttpStatus.CONFLICT, ErrorCodeEnum.L401);
    }

    @Test
    public final void testConsumptionsAlreadyCalculated() {
        final ConsumptionsAlreadyCalculatedException ex = ConsumptionsAlreadyCalculatedException.builder()
                .billId(TestUtils.BILL_ID_1)
                .build();

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleConsumptionsAlreadyCalculated(ex);

        testResponse(response, HttpStatus.CONFLICT, ErrorCodeEnum.L402);
    }

    private void testResponse(ResponseEntity<ErrorResponseDTO> response,
                              HttpStatus httpStatus, ErrorCodeEnum errorCodeEnum) {
        Assert.assertNotNull(response);
        Assert.assertEquals(httpStatus, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                errorCodeEnum.getErrorCode(),
                response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                errorCodeEnum.getErrorMessage(),
                response.getBody().getError()
        );
    }
}
