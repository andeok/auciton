package kr.jjust.auction.authentication.dto.request;

import jakarta.validation.constraints.NotNull;
import kr.jjust.auction.user.domain.User;

public record RegisterRequest(

	@NotNull
	String email,
	String password,
	String nickname
) {
	public User toUserEntity() {
		return new User(email, password, nickname);
	}

}
