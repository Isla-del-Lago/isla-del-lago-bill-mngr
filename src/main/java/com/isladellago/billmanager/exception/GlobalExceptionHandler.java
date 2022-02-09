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
        log.info("Invalid dates to create a new bill, start date: {}, end date: {}, uuid: {}",
                ex.getStartDate(), ex.getEndDate(), ex.getUuid());

        return mapErrorResponse(ErrorCodeEnum.L300, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponseDTO> handleInvalidArgument() {
        log.info(ErrorCodeEnum.L002.getErrorMessage());

        return mapErrorResponse(ErrorCodeEnum.L002, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BillExistsWithDateRangeException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBillExistsWithDateRange(
            BillExistsWithDateRangeException ex) {
        log.info("There is a bill with the start date: {} or end date: {}, uuid: {}",
                ex.getStartDate(), ex.getEndDate(), ex.getUuid());

        return mapErrorResponse(ErrorCodeEnum.L301, HttpStatus.CONFLICT);
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
