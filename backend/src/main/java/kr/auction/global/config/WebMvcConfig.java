package kr.auction.global.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.auction.auth.config.UserPrincipalArgumentResolver;
import kr.auction.auth.controller.cookie.CookieResolver;
import kr.auction.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final CookieResolver cookieResolver;
	private final AuthService authService;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new UserPrincipalArgumentResolver(cookieResolver, authService));
	}
}
