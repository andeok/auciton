package kr.auction.bid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.auction.bid.domain.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
