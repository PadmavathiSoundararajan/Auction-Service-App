package com.auction.bidding.externalAPIs;

import com.auction.bidding.model.Bid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Bidding-Query-Service",url = "${url.bidding.service}")
public interface BiddingClient {

    @GetMapping("/e-auction/api/v1/buyer/getBid/{productId}/{email}")
    Bid getBidsByEmailAndProductId(@PathVariable String productId,@PathVariable String email);
}
