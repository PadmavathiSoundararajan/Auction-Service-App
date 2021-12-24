package com.auction.bidding.query;

public class GetBidsByProductIdQuery {

    private String productId ;

    public GetBidsByProductIdQuery() {
    }

    public GetBidsByProductIdQuery(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
