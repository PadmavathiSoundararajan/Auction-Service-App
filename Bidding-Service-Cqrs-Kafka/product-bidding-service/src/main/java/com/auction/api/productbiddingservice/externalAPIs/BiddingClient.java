package com.auction.api.productbiddingservice.externalAPIs;

import com.auction.api.productbiddingservice.model.Bid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Bidding-Query-Service", url = "${url.bidding.service}")
public interface BiddingClient {

    @GetMapping("/e-auction/api/v1/buyer/getBid/{productId}")
    List<Bid> getAllBidsByProductId(@PathVariable String productId);
}
