package kr.auction.emotion.service.gemini;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import kr.auction.emotion.dto.request.AiFeedbackRequest;
import kr.auction.emotion.dto.response.AiFeedbackResponse;

@Component
public class GeminiClient {

	private final RestClient restClient;

	public GeminiClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public AiFeedbackResponse getAiFeedback(AiFeedbackRequest aiFeedbackRequest) {

		String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
		String headerName = "X-goog-api-key";
		//TODO: API키 비밀키로 수정 예정
		String apiKey = "";

		return restClient.post()
			.uri(uri)
			.contentType(MediaType.APPLICATION_JSON)
			.header(headerName, apiKey)
			.body(aiFeedbackRequest)
			.retrieve()
			.body(AiFeedbackResponse.class);
	}
}
