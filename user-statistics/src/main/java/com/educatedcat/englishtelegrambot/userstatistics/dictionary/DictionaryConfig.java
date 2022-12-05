package com.educatedcat.englishtelegrambot.userstatistics.dictionary;

import io.netty.channel.*;
import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.http.client.reactive.*;
import org.springframework.web.reactive.function.client.*;
import reactor.netty.http.client.*;

import java.nio.charset.*;
import java.time.*;

@Configuration
@RequiredArgsConstructor
public class DictionaryConfig {
	private final DictionaryProperties dictionaryProperties;
	
	@Bean
	public WebClient dictionaryWebClient() {
		final var httpClient = HttpClient.create()
		                                 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
		                                 .responseTimeout(Duration.ofSeconds(5));
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
		                          .defaultHeaders(httpHeaders ->
				                                          httpHeaders.add(HttpHeaders.CONTENT_TYPE,
				                                                          new MediaType(MediaType.APPLICATION_JSON,
				                                                                        StandardCharsets.UTF_8).toString()))
		                          .baseUrl(dictionaryProperties.getUrl().getApi())
		                          .build();
	}
}
