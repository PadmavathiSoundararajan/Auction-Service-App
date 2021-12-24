package com.auction.bidding.events;


public class UpdateBidAmountEvent extends AbstractEvent<String> {
    private String email;
    private String productId;
    private Long bidAmount;

    public UpdateBidAmountEvent(String id, String email, String productId, Long bidAmount) {
        super(id);
        this.email = email;
        this.productId = productId;
        this.bidAmount = bidAmount;
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
}

