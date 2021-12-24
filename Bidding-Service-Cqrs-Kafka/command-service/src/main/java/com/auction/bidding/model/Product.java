package com.auction.bidding.model;

public class Product {

    private String productId;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private String category;
    private Long startingPrice;
    private String bidEndDate;
    private Seller seller;

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Long startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(String bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", category=" + category +
                ", startingPrice=" + startingPrice +
                ", bidEndDate=" + bidEndDate +
                ", seller=" + seller +
                '}';
    }
}
