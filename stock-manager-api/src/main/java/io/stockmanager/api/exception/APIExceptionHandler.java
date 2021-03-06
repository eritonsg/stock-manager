package io.stockmanager.api.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ExceptionProblem.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fields.add(new ExceptionProblem.Field(name, message));
        }

        ExceptionProblem exceptionProblem = ExceptionProblem.builder()
                .status(status.value())
                .message("There are some invalid fields. Fill correctly all fields and try again.")
                .timeStamp(OffsetDateTime.now())
                .fields(fields)
                .build();

        return super.handleExceptionInternal(ex, exceptionProblem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ExceptionProblem> handleEntityNotFoundException(BusinessException ex) {
        ExceptionProblem exceptionProblem = ExceptionProblem.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timeStamp(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionProblem);
    }

}
