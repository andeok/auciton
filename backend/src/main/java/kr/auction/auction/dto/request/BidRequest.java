package kr.auction.auction.dto.request;

import kr.auction.auction.domain.Auction;
import kr.auction.bid.domain.Bid;
import kr.auction.user.domain.User;

public record BidRequest(
	int bidPrice
) {
	public Bid of(User user, Auction auction) {
		return new Bid(bidPrice, auction, user);
	}
}
