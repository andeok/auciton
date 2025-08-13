package kr.auction.global.exception.dto;

public record ExceptionResponse(String httpMethod, String path, String auctionCode, String message) {
}
