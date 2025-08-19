package kr.auction.emotion.dto.request;

import java.util.List;

public record AiFeedbackRequest(
	List<Content> contents

) {
	public record Content(List<Part> parts) {
	}

	public record Part(String text) {
	}

}
