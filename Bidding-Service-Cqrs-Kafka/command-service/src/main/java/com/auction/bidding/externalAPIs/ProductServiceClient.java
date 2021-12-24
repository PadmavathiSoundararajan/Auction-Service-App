package com.auction.bidding.externalAPIs;

import com.auction.bidding.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Product-Service",url = "${url.product.service}")
public interface ProductServiceClient {

    @GetMapping("/e-auction/api/v1/seller/getProduct/{productId}")
    Product getProduct(@PathVariable String productId);
}
