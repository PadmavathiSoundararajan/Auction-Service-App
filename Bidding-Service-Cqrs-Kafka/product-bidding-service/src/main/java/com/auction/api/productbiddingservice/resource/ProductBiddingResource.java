package com.auction.api.productbiddingservice.resource;

import com.auction.api.productbiddingservice.externalAPIs.BiddingClient;
import com.auction.api.productbiddingservice.externalAPIs.ProductServiceClient;
import com.auction.api.productbiddingservice.model.Bid;
import com.auction.api.productbiddingservice.model.Product;
import com.auction.api.productbiddingservice.model.ProductBids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("e-auction/api/v1/seller")
public class ProductBiddingResource {

    final Logger log = LoggerFactory.getLogger(ProductBiddingResource.class);

    @Autowired
    ProductServiceClient productServiceClient;

    @Autowired
    BiddingClient biddingClient;

    @GetMapping("/show-bids/{productId}")
    @CrossOrigin
    public ProductBids showBidsForProduct(@PathVariable String productId) {
        List<Bid> bidList = new ArrayList<>();
        Product product = new Product();
        try {
            log.info("Calling product-service to get product for product with id - {}", productId);
            product = productServiceClient.getProduct(productId);
            log.info("Product info available from  product-service for product with id - {}", productId);
            log.info("Calling bidding-query-service to get bids for product with id - {}", productId);
            bidList = biddingClient.getAllBidsByProductId(productId);
            log.info("NUmber of bids available for product with id - {} is {}", productId, bidList.size());
        } catch (Exception ex) {
            log.error("Error Occurred while fetching product and its related bids!");
        }
        return new ProductBids(product, bidList);
    }
}
