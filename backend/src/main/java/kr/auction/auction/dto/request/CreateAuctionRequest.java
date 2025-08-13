package kr.auction.auction.dto.request;

import java.time.LocalDateTime;

import kr.auction.auction.domain.Auction;
import kr.auction.product.domain.Product;
import kr.auction.user.domain.User;

public record CreateAuctionRequest(
	String title,
	String description,
	int startPrice,
	int endPrice,
	LocalDateTime startTime,
	LocalDateTime endTime
) {

	public Auction toAuction(Product product) {
		return new Auction(startPrice, endPrice, startTime, endTime, product);
	}

	public Product toProduct(User user){
		return new Product(title, description, user);
	}
}
