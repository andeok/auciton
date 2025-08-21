package kr.auction.auction.dto.response;

import java.util.List;

public record AuctionsResponses(
	List<AuctionsResponse> auctions
) {
}
