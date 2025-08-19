package kr.auction.global.exception.dto;

public record GeminiExceptionResponse(
	String error, String error_description, String error_code
) {
}
