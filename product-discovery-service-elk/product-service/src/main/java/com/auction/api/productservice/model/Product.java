package com.auction.api.productservice.model;

import com.auction.api.productservice.constraints.ProductBidEndDate;
import com.auction.api.productservice.constraints.ProductCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Document(collection = "Products")
public class Product {

    @Id
    private String productId;
    @NotNull(message = "Product Name cannot be null.")
    @Size(max=30,min=5,message="Product Name should have 5 to 30 characters")
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    @ProductCategory
    private String category;
    @Pattern(regexp = "[0-9]+",message = "Product Price can contain only numbers.")
    private String startingPrice;
    @NotNull(message = "The Bid end date is required.")
    @ProductBidEndDate
    private String bidEndDate;
    @Valid
    private Seller seller;

    public Product(String productId, String productName, String shortDescription, String detailedDescription, String category, String startingPrice, String bidEndDate, Seller seller) {
        this.productId = productId;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.category = category;
        this.startingPrice = startingPrice;
        this.bidEndDate = bidEndDate;
        this.seller = seller;
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

    public String getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(String startingPrice) {
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
