package com.auction.bidding.exceptionHandlers;

import com.auction.bidding.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse response = new ErrorResponse(((FieldError) error).getField(), "Error : "+error.getDefaultMessage());
            errorResponses.add(response);
        });
        return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
    }
}
