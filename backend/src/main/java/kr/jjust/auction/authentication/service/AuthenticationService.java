package kr.jjust.auction.authentication.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import kr.jjust.auction.authentication.dto.request.RegisterRequest;
import kr.jjust.auction.user.domain.Email;
import kr.jjust.auction.user.domain.User;
import kr.jjust.auction.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;

	public void register(@Valid RegisterRequest request) {
		// User user = processUser("local", request.toUserEntity(), true);
	}

}
