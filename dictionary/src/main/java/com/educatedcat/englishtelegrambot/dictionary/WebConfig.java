package com.educatedcat.englishtelegrambot.dictionary;

import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.reactive.accept.*;
import org.springframework.web.reactive.config.*;

import java.nio.charset.*;

@Configuration
public class WebConfig implements WebFluxConfigurer {
	@Override
	public void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {
		WebFluxConfigurer.super.configureContentTypeResolver(builder);
		builder.fixedResolver(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8));
	}
}
