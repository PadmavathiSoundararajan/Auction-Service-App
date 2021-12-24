package com.auction.api.productservice.externalAPIs;

import com.auction.api.productservice.model.Bid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Bidding-Service",url = "${fdmobile.ribbon.listOfServers}")
public interface BiddingClient {

    @GetMapping("/e-auction/api/v1/buyer/getBid/{productId}")
    List<Bid> getAllBidsByProductId(@PathVariable String productId);
}
