package kr.getz.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.lettuce.core.dynamic.annotation.Param;
import kr.getz.auction.domain.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

	@Query("SELECT a FROM Auction a JOIN FETCH a.product p JOIN FETCH p.user")
	List<Auction> findAllWithProductAndUser();

	@Query("SELECT a FROM Auction a JOIN FETCH a.product p JOIN FETCH p.user LEFT JOIN FETCH a.bids b WHERE a.id = :id")
	Optional<Auction> findByProductAndUser(@Param("id") long id);

}
