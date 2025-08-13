package kr.auction.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.auction.global.exception.AuctionException;
import kr.auction.global.exception.ExceptionCode;
import kr.auction.user.domain.Email;
import kr.auction.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	default User getUserById(Long id) {
		return findById(id).orElseThrow(() -> new AuctionException(ExceptionCode.USER_NOT_FOUND));
	}

	Optional<User> findByEmail(Email email);
}
