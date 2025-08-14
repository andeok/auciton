package kr.jjust.auction.authentication.controller;

import jakarta.validation.Valid;
import kr.jjust.auction.authentication.controller.dto.request.LoginTokenRequest;
import kr.jjust.auction.authentication.dto.request.RegisterRequest;
import kr.jjust.auction.authentication.dto.response.LoginInfomationResponse;
import kr.jjust.auction.authentication.infrastructure.oauth2.Oauth2Type;
import kr.jjust.auction.authentication.service.AuthenticationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping("/oauth2/login/{oauth2Type}")
	public ResponseEntity<LoginInfomationResponse> login(@PathVariable String oauth2Type,
		@RequestBody @Valid LoginTokenRequest request) {

		// TODO : 우선 로컬 회원가입 먼저.
		log.info("login {} {}", oauth2Type, request);
		return null;
	}

	@PostMapping("/v1/local-auth/register")
	public ResponseEntity<LoginInfomationResponse> register(@RequestBody @Valid RegisterRequest request) {

		authenticationService.register(request);

		return null;
	}

}
