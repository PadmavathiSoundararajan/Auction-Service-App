package com.auction.bidding.resource;

import com.auction.bidding.model.Bid;
import com.auction.bidding.service.BiddingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/e-auction/api/v1/buyer")
public class BiddingResource {

    private static final Logger LOG = LoggerFactory.getLogger(BiddingResource.class);


    @Autowired
    BiddingService biddingService;

    @PostMapping("/add-Bid")
    public ResponseEntity<String> addBid(@Valid @RequestBody Bid bid) {
        try {
            LOG.info("Request received to add bid for product id : {}", bid.getProductId());
            String response = biddingService.addBid(bid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Bid could not be placed due to an error - " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/bid-amount/{productId}/{buyerEmailld}/{newBidAmount}")
    public String updateBidAmountForProductId(@PathVariable String productId, @PathVariable String buyerEmailld, @PathVariable String newBidAmount) {
        try {
            biddingService.updateBid(productId, buyerEmailld, newBidAmount);
            return "Bid amount updated successfully for product id : " + productId;
        } catch (Exception ex) {
            return "Bid amount could not be updated due to an error - " + ex.getMessage();
        }
    }

}
