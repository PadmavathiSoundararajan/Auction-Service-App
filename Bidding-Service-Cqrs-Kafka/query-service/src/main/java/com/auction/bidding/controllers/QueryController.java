package com.auction.bidding.controllers;

import com.auction.bidding.query.GetAllBidsQuery;
import com.auction.bidding.dto.Bid;
import com.auction.bidding.query.GetBidsByProductIdQuery;
import com.auction.bidding.repository.BiddingRepository;
import com.auction.bidding.responses.FindBidsQueryResponse;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e-auction/api/v1/buyer")
public class QueryController {

    private final QueryGateway queryGateway;

    @Autowired
    BiddingRepository biddingRepository;

    public QueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/getAllBids")
    public ResponseEntity<FindBidsQueryResponse> getAllBids() {
        FindBidsQueryResponse cards = queryGateway.query(
                new GetAllBidsQuery(), FindBidsQueryResponse.class
        ).join();

        if (cards == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/getBid/{productId}")
    public ResponseEntity getAllBidsByProductId(@PathVariable String productId) {
        FindBidsQueryResponse cards = queryGateway.query(
                new GetBidsByProductIdQuery(productId), FindBidsQueryResponse.class
        ).join();

        if (cards == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cards.getBids(), HttpStatus.OK);
    }

    @GetMapping("/getBid/{productId}/{email}")
    public Bid getBidsByEmailAndProductId(@PathVariable String productId, @PathVariable String email)
    {
        return biddingRepository.findByEmailAndProductId(email,productId);
    }
}
