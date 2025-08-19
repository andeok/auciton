package kr.auction.emotion.dto.response;

import java.util.List;

public record AiFeedbackResponse(
	List<Candidate> candidates

) {
	public record Candidate(
		Content content,
		String finishReason,
		int index
	) {}

	// Record for the content within a candidate
	public record Content(
		List<Part> parts,
		String role
	) {}

	// Record for a part within content
	public record Part(
		String text
	) {}
}
