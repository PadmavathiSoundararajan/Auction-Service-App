package com.auction.api.productservice.resource;

import com.auction.api.productservice.model.Product;
import com.auction.api.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("e-auction/api/v1/seller")
public class ProductResource {

    final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public String addProduct(@Valid @RequestBody Product product) {
        log.info("Starting to add product...");
        Product savedProduct = productService.addProduct(product);
        log.info("Product Added Succuessfully .Product ID is : {}", savedProduct.getProductId());
        return "Product Added Succuessfully .Product ID is :" + savedProduct.getProductId();
    }

    @GetMapping("/getAllProducts")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/getProduct/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        try {
            log.info("Starting to delete product with product id : {}", productId);
            String response = productService.deleteProduct(productId);
            return response;
        } catch (Exception ex) {
            log.error("Product with product id : () could not be deleted due to : {}", productId, ex);
            return "Product could not be deleted due to an error - " + ex;
        }
    }

    @GetMapping("/deleteAllProducts")
    public String deleteProducts() {
        productService.deleteProducts();
        return "All Products Deleted Succuessfully!";
    }
}
