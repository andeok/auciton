package kr.auction.emotion.dto.request;

public record EmotionSaveRequest(
	String content,
	int myScore
) {
}
