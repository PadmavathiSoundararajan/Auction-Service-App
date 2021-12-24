package com.auction.bidding.query.queryhandlers;

import com.auction.bidding.events.eventhandlers.BidsEventHandler;
import com.auction.bidding.query.GetAllBidsQuery;
import com.auction.bidding.dto.Bid;
import com.auction.bidding.query.GetBidsByProductIdQuery;
import com.auction.bidding.repository.BiddingRepository;
import com.auction.bidding.responses.FindBidsQueryResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BidsQueryHandler {

    private final Logger log = LoggerFactory.getLogger(BidsEventHandler.class);
    @Autowired
    BiddingRepository biddingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;

    @QueryHandler
    public FindBidsQueryResponse handle(GetAllBidsQuery query) {
        log.info("Handling GetAllBidsQuery...");
        List<Bid> bids = biddingRepository.findAll();
        return new FindBidsQueryResponse(bids);
    }

    @QueryHandler
    public FindBidsQueryResponse handle(GetBidsByProductIdQuery query) {
        log.info("Handling GetBidsByProductIdQuery...");
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("productId").is(query.getProductId())).with(Sort.by(Sort.Direction.DESC, "bidAmount"));
        List<Bid> bids = mongoTemplate.find(query1, Bid.class);
        return new FindBidsQueryResponse(bids);
    }
}