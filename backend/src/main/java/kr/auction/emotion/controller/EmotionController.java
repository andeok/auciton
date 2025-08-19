package kr.auction.emotion.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.auction.emotion.domain.Emotion;
import kr.auction.emotion.dto.request.EmotionSaveRequest;
import kr.auction.emotion.service.EmotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/emotions")
@RequiredArgsConstructor
public class EmotionController {

	private final EmotionService emotionService;


	@PostMapping
	public ResponseEntity<Emotion> save(@RequestBody EmotionSaveRequest request) {

		log.info("save request: {}", request);

		Long emotionId = emotionService.save(request);

		return ResponseEntity.created(URI.create("/v1/emotions/" + emotionId)).build();
	}
}
