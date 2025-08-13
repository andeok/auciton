package kr.auction.auction.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import kr.auction.auction.domain.Auction;
import kr.auction.auction.dto.request.BidRequest;
import kr.auction.auction.dto.request.CreateAuctionRequest;
import kr.auction.auction.dto.response.AuctionResponse;
import kr.auction.auction.dto.response.AuctionsResponse;
import kr.auction.auction.dto.response.AuctionsResponses;
import kr.auction.auction.repository.AuctionRepository;
import kr.auction.bid.domain.Bid;
import kr.auction.bid.repository.BidRepository;
import kr.auction.global.exception.AuctionException;
import kr.auction.global.exception.ExceptionCode;
import kr.auction.product.domain.Product;
import kr.auction.product.repository.ProductRepository;
import kr.auction.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionService {

	private final AuctionRepository auctionRepository;
	private final ProductRepository productRepository;
	private final BidRepository bidRepository;

	@Transactional
	public long createAuction(User user, @Valid CreateAuctionRequest request) {

		Product product = request.toProduct(user);
		Product saveProduct = productRepository.save(product);

		Auction auction = request.toAuction(saveProduct);
		Auction saveAuction = auctionRepository.save(auction);

		return saveAuction.getId();
	}

	@Transactional(readOnly = true)
	public AuctionsResponses getAuctionList() {
		List<AuctionsResponse> response = auctionRepository.findAllWithProductAndUser().stream()
			.map(AuctionsResponse::from)
			.toList();

		return new AuctionsResponses(response);
	}

	@Transactional(readOnly = true)
	public AuctionResponse getAuction(long id) {
		Auction auction = auctionRepository.findByProductAndUser(id)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUCTION_NOT_FOUND));

		return AuctionResponse.from(auction);
	}

	@Transactional
	public AuctionResponse placeBid(User user, BidRequest request, long id) {
		Auction auction = auctionRepository.findByProductAndUser(id)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUCTION_NOT_FOUND));

		// TODO : 입찰 조건 검증 하기(현재가보다 낮은지, 경매 상태, 2번 연속 입찰인지 등등...)
		validateBidPrice(user, auction, request);

		Bid newBid = request.of(user, auction);
		Bid savedBid = bidRepository.save(newBid);

		auction.getBids().add(savedBid);

		auction.updateCurrentPriceAndUser(user, request);

		return AuctionResponse.from(auction);
	}

	private void validateBidPrice(User user, Auction auction, BidRequest request) {
		auction.validateBidPrice(user, request);
	}
}
