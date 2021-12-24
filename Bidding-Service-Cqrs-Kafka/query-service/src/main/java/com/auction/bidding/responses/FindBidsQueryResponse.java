package com.auction.bidding.responses;

import com.auction.bidding.dto.Bid;

import java.util.List;

public class FindBidsQueryResponse {

    private List<Bid> bids;

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public FindBidsQueryResponse(List<Bid> bids) {
        this.bids = bids;
    }
}
