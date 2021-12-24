package com.auction.api.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BidsAvailableForProductException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BidsAvailableForProductException(String exception) {
        super(exception);
    }

}
