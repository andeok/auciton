package kr.getz.auction.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import kr.getz.BaseEntity;
import kr.getz.auction.dto.request.BidRequest;
import kr.getz.bid.domain.Bid;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.product.domain.Product;
import kr.getz.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Auction extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title; // 경매 제목
	private int startPrice;
	private int endPrice;
	private int currentPrice;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Enumerated(EnumType.STRING)
	private AuctionStatus status; // 경매 상태

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;

	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Bid> bids = new ArrayList<>();

	public Auction(String title, int startPrice, int endPrice, LocalDateTime startTime, LocalDateTime endTime, Product product) {
		this.title = title;
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.currentPrice = startPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.product = product;
		this.status = LocalDateTime.now().isAfter(startTime) ? AuctionStatus.ONGOING : AuctionStatus.SCHEDULED;
	}

	public void addBid(Bid bid) {
		this.bids.add(bid);
		bid.setAuction(this);
	}

	public void validateBidPrice(User user, BidRequest request) {
		if(this.currentPrice >= request.bidPrice()) {
			throw new AuctionException(ExceptionCode.BID_PRICE_ERROR);
		}

		// if(this.highestBidder != null && this.highestBidder.getId().equals(user.getId())){
		// 	throw new AuctionException(ExceptionCode.BID_USER_ERROR);
		// }
	}

	public void updateCurrentPriceAndUser(BidRequest request) {
		this.currentPrice = request.bidPrice();
	}
}
