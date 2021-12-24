package com.auction.bidding.service;

import com.auction.bidding.commands.AddBidCommand;
import com.auction.bidding.commands.UpdateBidAmountCommand;
import com.auction.bidding.exceptionHandlers.BidAlreadyExistsForUserException;
import com.auction.bidding.exceptionHandlers.BidEndDateReachedForProductException;
import com.auction.bidding.externalAPIs.BiddingClient;
import com.auction.bidding.externalAPIs.ProductServiceClient;
import com.auction.bidding.model.Bid;
import com.auction.bidding.model.Product;
import com.auction.bidding.utils.CommonUtility;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class BiddingService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    ProductServiceClient productServiceClient;

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    BiddingClient biddingClient;

    public String addBid(Bid bid) throws BidEndDateReachedForProductException, BidAlreadyExistsForUserException {
        Product product = productServiceClient.getProduct(bid.getProductId());
        Bid bidForProductIdAndEmailId = biddingClient.getBidsByEmailAndProductId(bid.getProductId(), bid.getEmail());
        if (product != null) {
            if (CommonUtility.isTodayBeforeOrAfterTheValueDate(product.getBidEndDate(), CommonUtility.BEFORE_AFTER_ENUM.AFTER)) {
                throw new BidEndDateReachedForProductException("Bid cannot be placed as bid end date cutoff is over!");
            }
            if (bidForProductIdAndEmailId != null) {
                throw new BidAlreadyExistsForUserException("Bid already exists for user with email id - " + bid.getEmail());
            }
            AddBidCommand command = new AddBidCommand(UUID.randomUUID().toString(), bid.getFirstName(), bid.getFirstName(), bid.getAddress(), bid.getCity(), bid.getState(), bid.getPin(), bid.getPhone(), bid.getEmail(), bid.getProductId(), bid.getBidAmount());
            String result = commandGateway.sendAndWait(command);
            return result;
        } else {
            return "Product with  product Id : " + bid.getProductId() + " does not exist!";
        }
    }

   /* public List<Bid> getAllBidsByProductId(@PathVariable String productId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId)).with(Sort.by(Sort.Direction.DESC, "bidAmount"));
        return mongoTemplate.find(query, Bid.class);
    }*/

   /* public List<Bid> getAllBids() {
        return biddingRepository.findAll();
    }

    public void deleteAllBids() {
        biddingRepository.deleteAll();
    }*/

   /* public void deleteAllBidsByProductId(@PathVariable String productId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));
        mongoTemplate.remove(query, Bid.class);
    }*/

    public void updateBid(String productId, String buyerEmailId, String newBidAmount) throws BidEndDateReachedForProductException {
        Product product = productServiceClient.getProduct(productId);
        if (CommonUtility.isTodayBeforeOrAfterTheValueDate(product.getBidEndDate(), CommonUtility.BEFORE_AFTER_ENUM.AFTER)) {
            throw new BidEndDateReachedForProductException("Bid Amount cannot be updated as bid end date cutoff is over!");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId)).addCriteria(Criteria.where("email").is(buyerEmailId));
        List<Bid> bids = mongoOperations.find(query, Bid.class);

        String bidId = biddingClient.getBidsByEmailAndProductId(productId,buyerEmailId).getId();

        UpdateBidAmountCommand command = new UpdateBidAmountCommand(bidId, buyerEmailId, productId, Long.valueOf(newBidAmount));
        String result = commandGateway.sendAndWait(command);
    }

}
