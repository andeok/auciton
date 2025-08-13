package kr.auction.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import kr.auction.user.domain.User;
import kr.auction.user.domain.UserType;

public record RegisterRequest(

	@NotNull
	String email,
	String password,
	String nickname
) {
	public User toUserEntity() {
		return new User(email, password, nickname);
	}

	public User toRegisterEntity() {
		return new User(email, password, nickname, UserType.USER);
	}

}
