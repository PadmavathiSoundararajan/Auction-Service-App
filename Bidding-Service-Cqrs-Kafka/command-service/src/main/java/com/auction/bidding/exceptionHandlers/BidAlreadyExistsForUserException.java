package com.auction.bidding.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BidAlreadyExistsForUserException extends Exception {
    private static final long serialVersionUID = 1L;

    public BidAlreadyExistsForUserException(String exception) {
        super(exception);
    }

}
