package com.auction.bidding.events;

import org.springframework.util.Assert;

public abstract class AbstractEvent<T> {

    private T id;

    public AbstractEvent(T id) {
        Assert.notNull(id, "Id must be not null");
        this.id = id;
    }

    public AbstractEvent() {

    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
