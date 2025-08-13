package kr.auction.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import kr.auction.global.handler.GeminiExceptionHandler;
import kr.auction.global.handler.GlobalExceptionHandler;

@Configuration
public class GeminiClientConfig {

	@Bean
	RestClient restClient(GeminiExceptionHandler geminiExceptionHandler) {
		return RestClient.builder()
			.defaultStatusHandler(geminiExceptionHandler)
			.build();
	}
}
