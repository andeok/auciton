package kr.auction.global.exception;

import kr.auction.global.exception.dto.GeminiExceptionResponse;

public class GeminiException extends RuntimeException {

	private GeminiExceptionResponse response;

	public GeminiException(GeminiExceptionResponse response) {
		this.response = response;
	}

	public GeminiExceptionResponse getResponse() {
		return response;
	}
}
