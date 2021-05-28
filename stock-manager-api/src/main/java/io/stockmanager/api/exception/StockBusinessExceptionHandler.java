package io.stockmanager.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class StockBusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StockBusinessException.class)
    protected ResponseEntity<ExceptionProblem> handleStockPersistenceSecurity(BusinessException ex) {
        ExceptionProblem exceptionProblem = ExceptionProblem.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(ex.getMessage())
                .timeStamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionProblem);
    }

}
