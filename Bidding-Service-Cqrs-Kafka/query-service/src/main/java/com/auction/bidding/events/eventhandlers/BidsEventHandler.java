package com.auction.bidding.events.eventhandlers;

import com.auction.bidding.dto.Bid;
import com.auction.bidding.events.BidAddedEvent;
import com.auction.bidding.events.UpdateBidAmountEvent;
import com.auction.bidding.repository.BiddingRepository;
import com.mongodb.client.result.UpdateResult;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

/*
 If ProcessingGroup is not set, then the package name is used.
 The name of the ProcessingGroup is also in the configuration
 and the name must match the name of the TrackingProcessor defined
 in the configuration. Axon adds automatically all the handler to the TrackingProcessor
 The class must be a @Component
 */
@Component
@ProcessingGroup(value = "BidsProcessor")
public class BidsEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BidsEventHandler.class);

    @Autowired
    private BiddingRepository biddingRepository;

    @Autowired
    MongoOperations mongoOperations;

    @EventHandler
    public void on(BidAddedEvent myEvent, @Timestamp Instant instant) {
        LOGGER.info("got the add event {}", myEvent);
        Bid card = new Bid(myEvent.getId(), myEvent.getFirstName(), myEvent.getLastName(), myEvent.getAddress(), myEvent.getCity(), myEvent.getState(), myEvent.getPin(), myEvent.getPhone(), myEvent.getEmail(), myEvent.getProductId(), myEvent.getBidAmount(),instant.toString());
        biddingRepository.save(card);
    }

    @EventHandler
    public void on(UpdateBidAmountEvent myEvent, @Timestamp Instant instant) {
        LOGGER.info("got the update event {}", myEvent);
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(myEvent.getProductId())).addCriteria(Criteria.where("email").is(myEvent.getEmail()));
        List<Bid> bids = mongoOperations.find(query, Bid.class);
        Update update = new Update();
        update.set("bidAmount", myEvent.getBidAmount());
        update.set("lastUpdated",instant.toString());
        UpdateResult result = mongoOperations.updateMulti(query, update, Bid.class);
    }
}
