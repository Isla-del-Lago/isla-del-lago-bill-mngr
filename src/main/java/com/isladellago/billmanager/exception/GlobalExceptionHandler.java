package com.isladellago.billmanager.exception;

import com.isladellago.billmanager.domain.dto.ErrorResponseDTO;
import com.isladellago.billmanager.domain.enums.ErrorCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBillDateRangeException.class)
    public final ResponseEntity<ErrorResponseDTO> handleInvalidBillDateRange(
            InvalidBillDateRangeException ex) {
        log.error("Invalid dates to create a new bill, start date: {}, end date: {}, uuid: {}",
                ex.getStartDate(), ex.getEndDate(), ex.getUuid());

        return mapErrorResponse(ErrorCodeEnum.L300, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponseDTO> handleInvalidArgument(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        return mapErrorResponse(ErrorCodeEnum.L002, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BillExistsWithDateRangeException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBillExistsWithDateRange(
            BillExistsWithDateRangeException ex) {
        log.error("There is a bill with the start date: {} or end date: {}, uuid: {}",
                ex.getStartDate(), ex.getEndDate(), ex.getUuid());

        return mapErrorResponse(ErrorCodeEnum.L301, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BillNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBillNotFound(
            BillNotFoundException ex) {
        if (ex.getBillId() != null) {
            log.error("Bill with id: {} not found", ex.getBillId());
        } else {
            log.error("Bill with startDate: {} and endDate: {} not found",
                    ex.getStartDate(), ex.getEndDate());
        }

        return mapErrorResponse(ErrorCodeEnum.L302, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApartmentNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handleApartmentNotFoundException(
            ApartmentNotFoundException ex) {
        log.error("Apartment with id: {} not found", ex.getApartmentId());

        return mapErrorResponse(ErrorCodeEnum.L200, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsumptionNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handleConsumptionNotFoundException(
            ConsumptionNotFoundException ex) {
        log.error("Consumption with id: {} not found", ex.getConsumptionId());

        return mapErrorResponse(ErrorCodeEnum.L400, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsumptionExistsWithBillIdAndApartmentId.class)
    public final ResponseEntity<ErrorResponseDTO> handleConsumptionExistsWithBillIdAndApartmentId(
            ConsumptionExistsWithBillIdAndApartmentId ex) {
        log.error("The consumption with bill id: {} and apartment id: {} already exists",
                ex.getBillId(), ex.getApartmentId());

        return mapErrorResponse(ErrorCodeEnum.L401, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConsumptionsAlreadyCalculatedException.class)
    public final ResponseEntity<ErrorResponseDTO> handleConsumptionsAlreadyCalculated(
            ConsumptionsAlreadyCalculatedException ex) {
        log.error("Consumptions for the bill with id: {} are already calculated", ex.getBillId());

        return mapErrorResponse(ErrorCodeEnum.L402, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConsumptionDetailsNotFoundByBillId.class)
    public final ResponseEntity<ErrorResponseDTO> handleConsumptionDetailsNotFoundByBillId(
            ConsumptionDetailsNotFoundByBillId ex) {
        log.error("Consumptions not calculated for bill id: {}", ex.getBillId());

        return mapErrorResponse(ErrorCodeEnum.L403, HttpStatus.NOT_FOUND);
    }

    /**
     * Maps the error response when handle an exception.
     *
     * @param errorCodeEnum Error code.
     * @param httpStatus    Response status.
     * @return Response entity mapped.
     */
    private ResponseEntity<ErrorResponseDTO> mapErrorResponse(
            ErrorCodeEnum errorCodeEnum, HttpStatus httpStatus) {
        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(errorCodeEnum.getErrorMessage())
                .errorCode(errorCodeEnum.getErrorCode())
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(errorResponseDTO);
    }
}
