package com.auction.bidding.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class UpdateBidAmountCommand {

    @TargetAggregateIdentifier
    private String id;
    private String email;
    private String productId;
    private Long bidAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public UpdateBidAmountCommand(String id, String email, String productId, Long bidAmount) {
        this.id = id;
        this.email = email;
        this.productId = productId;
        this.bidAmount = bidAmount;
    }
}
