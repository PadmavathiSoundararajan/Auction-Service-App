package com.auction.bidding.model;

public class ErrorResponse
{
    public ErrorResponse(String field, String message) {
        super();
        this.message = message;
        this.field = field;
    }

    private String message;
    private String field;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}