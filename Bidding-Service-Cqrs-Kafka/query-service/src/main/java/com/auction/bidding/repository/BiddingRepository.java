package com.auction.bidding.repository;

import com.auction.bidding.dto.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BiddingRepository extends MongoRepository<Bid, Integer> {

    Bid findByEmailAndProductId(String email, String productId);

}
