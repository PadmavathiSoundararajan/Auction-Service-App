package com.auction.api.productbiddingservice.model;

import java.util.List;

public class ProductBids {

    Product product;
    List<Bid> bids;

    public ProductBids(Product product, List<Bid> bids) {
        this.product = product;
        this.bids = bids;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

}
