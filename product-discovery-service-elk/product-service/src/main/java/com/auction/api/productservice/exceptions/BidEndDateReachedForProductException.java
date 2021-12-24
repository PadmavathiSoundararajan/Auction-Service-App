package com.auction.api.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BidEndDateReachedForProductException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BidEndDateReachedForProductException(String exception) {
        super(exception);
    }

}

