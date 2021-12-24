package com.auction.api.productservice;

import com.auction.api.productservice.externalAPIs.BiddingClient;
import com.auction.api.productservice.model.Product;
import com.auction.api.productservice.model.Seller;
import com.auction.api.productservice.repository.ProductRepository;
import com.auction.api.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ProductServiceTest {

    ProductRepository productRepository;

    MongoTemplate mongoTemplate;

    BiddingClient biddingClient;

    ProductService productService;

    @BeforeEach
    public void setUP() {
        productRepository = Mockito.mock(ProductRepository.class);
        mongoTemplate = Mockito.mock(MongoTemplate.class);
        biddingClient = Mockito.mock(BiddingClient.class);
        productService = new ProductService();
        productService.setBiddingClient(biddingClient);
        productService.setMongoTemplate(mongoTemplate);
        productService.setProductRepository(productRepository);
    }

    @Test
    public void test_add_product() {
        Seller seller = new Seller("XYZ", "ABC", "1234 no 21,vasant vihar", "chennai", "tamilnadu", "455464", "1232423445", "XYZ@gmail.com");
        Product product = new Product("123", "abc", "abc product", "Art related product", "PAINTING", "100", "31-12-2021", seller);
        when(productRepository.save(any())).thenReturn(product);
        Assert.notNull(productService.addProduct(product));
    }

    @Test
    public void test_GetProduct() {
        Seller seller = new Seller("XYZ", "ABC", "1234 no 21,vasant vihar", "chennai", "tamilnadu", "455464", "1232423445", "XYZ@gmail.com");
        Product product = new Product("123", "abc", "abc product", "Art related product", "PAINTING", "100", "31-12-2021", seller);
        when(productRepository.findById("123")).thenReturn(Optional.of(product));
        Assert.notNull(productService.getProduct("123"));
    }

    @Test
    public void test_DeleteProduct() {
        Seller seller = new Seller("XYZ", "ABC", "1234 no 21,vasant vihar", "chennai", "tamilnadu", "455464", "1232423445", "XYZ@gmail.com");
        Product product = new Product("123", "abc", "abc product", "Art related product", "PAINTING", "100", "31-12-2021", seller);
        when(productRepository.findById("123")).thenReturn(Optional.of(product));
        when( biddingClient.getAllBidsByProductId("123")).thenReturn(new ArrayList<>());
        Assert.notNull(productService.deleteProduct("123"));
    }
}
