package com.kruger.hero.error;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.kruger.hero.ResponseVO;

/**
 * ResponseExceptionHandler.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    public ResponseExceptionHandler() {
        super();
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String response = "{\"success\": false, \"code\": 400, \"message\": \"" + ex.getMessage() + "\"}";
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ResponseVO response = ResponseVO.builder().errors(errors).code(status.value()).success(false).message(status.getReasonPhrase()).build();
        return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }
}
