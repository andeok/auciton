package kr.auction.emotion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.auction.emotion.dto.request.AiFeedbackRequest;
import kr.auction.emotion.dto.request.EmotionSaveRequest;
import kr.auction.emotion.dto.response.AiFeedbackResponse;
import kr.auction.emotion.repository.EmotionRepository;
import kr.auction.emotion.service.gemini.GeminiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionService {

	private final EmotionRepository emotionRepository;
	private final GeminiClient aiFeedbackClient;

	@Transactional
	public Long save(EmotionSaveRequest request) {

		String text = "너는 유명한 심리상담가야. 나는 매일 감정일기를 작성중이야."
			+ "다음 //기호 뒤에 내가 생각한 감정 점수와 감정 내용을 적을거야. 점수는 낮을수록 기분이 좋지 않은거라고 판단해."
			+ "너는 그걸 판단해서 너가 생각한 점수를 알려주고 300자 이내로 조언이나 격려, 따끔한 충고를 해줘."
			+ "너가 나에게 전달해줄 양식은 점수 : x점, 내용 : 내용 이렇게 보내줘"
			+ "// 나의 점수 : " + request.myScore() + ", " + request.content();

		AiFeedbackRequest.Part part = new AiFeedbackRequest.Part(text);
		AiFeedbackRequest.Content content = new AiFeedbackRequest.Content(List.of(part));
		AiFeedbackRequest geminiRequest = new AiFeedbackRequest(List.of(content));

		AiFeedbackResponse aiFeedback = aiFeedbackClient.getAiFeedback(geminiRequest);


		return null;
	}

}
