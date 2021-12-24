package com.auction.api.productservice.service;

import com.auction.api.productservice.exceptions.BidEndDateReachedForProductException;
import com.auction.api.productservice.exceptions.BidsAvailableForProductException;
import com.auction.api.productservice.externalAPIs.BiddingClient;
import com.auction.api.productservice.model.Bid;
import com.auction.api.productservice.model.Product;
import com.auction.api.productservice.repository.ProductRepository;
import com.auction.api.productservice.utils.CommonUtility;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {
    final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    BiddingClient biddingClient;

    public Product addProduct(Product product) {
        Product responseproduct = productRepository.save(product);
        log.info("Product with id - " + responseproduct.getProductId() + " added succesfully!");
        return responseproduct;
    }

    public Product getProduct(String productId) {
        log.info("Getting product by id : - {}",productId);
        return productRepository.findById(productId).get();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "getAllBidsByProductIdInfoFallback")
    public String deleteProduct(String productId) throws BidsAvailableForProductException, BidEndDateReachedForProductException {
        log.info("Deleting product with id - {} started...", productId);
        Product product = productRepository.findById(productId).get();
        List<Bid> bidListForProductId = biddingClient.getAllBidsByProductId(productId);
        log.info("Bids found for product id - {}", productId);
        if (CommonUtility.isTodayBeforeOrAfterTheValueDate(product.getBidEndDate(), CommonUtility.BEFORE_AFTER_ENUM.AFTER)) {

            throw new BidEndDateReachedForProductException("Product cannot be deleted as bid end date cutoff is over!");
        }
        if (bidListForProductId.size() > 0) {
            throw new BidsAvailableForProductException("Product has still one or more bids associated with it.");
        }
        productRepository.deleteById(productId);
        log.info("Product with id - " + productId + " deleted succesfully!");
        return "Product with id - " + productId + " deleted succesfully!";
    }


    public String getAllBidsByProductIdInfoFallback(Exception e) {
        return "Error : " + e;
    }

    public void deleteProducts() {
        productRepository.deleteAll();
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public BiddingClient getBiddingClient() {
        return biddingClient;
    }

    public void setBiddingClient(BiddingClient biddingClient) {
        this.biddingClient = biddingClient;
    }
}
