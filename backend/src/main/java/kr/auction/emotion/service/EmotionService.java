package kr.auction.emotion.service;


import java.util.List;
import kr.auction.emotion.dto.request.AiFeedbackRequest;
import kr.auction.emotion.dto.request.EmotionSaveRequest;
import kr.auction.emotion.dto.response.AiFeedbackResponse;
import kr.auction.emotion.repository.EmotionRepository;
import kr.auction.emotion.service.gemini.GeminiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmotionService {

	private final EmotionRepository emotionRepository;
	private final GeminiClient aiFeedbackClient;

	@Transactional
	public Long save(EmotionSaveRequest request) {

        String text = "너는 내 이야기를 깊이 공감해주고, 내 편이 되어주는 따뜻한 심리 상담가야. "
            + "판단하거나 비난하지 않고, 내 감정을 최우선으로 존중해줘. //기호 다음에 내 이야기를 적을게."
            + "//" + request.content()
            + "일기의 내용을 항상 처음 본다 생각하고 얘기를 듣고 공감해줘. "
            + "화가나는 일에는 같이 화내주고 충고가 필요한 일에는 충고를 해줘. 내용은 200자 내외로 해줘.";

        AiFeedbackRequest.Part part = new AiFeedbackRequest.Part(text);
		AiFeedbackRequest.Content content = new AiFeedbackRequest.Content(List.of(part));
		AiFeedbackRequest geminiRequest = new AiFeedbackRequest(List.of(content));

		AiFeedbackResponse aiFeedback = aiFeedbackClient.getAiFeedback(geminiRequest);

        log.info("aiFeedback: {}", aiFeedback);

		return null;
	}

}
