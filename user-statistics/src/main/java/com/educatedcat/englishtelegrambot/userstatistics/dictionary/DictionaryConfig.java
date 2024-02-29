package com.educatedcat.englishtelegrambot.userstatistics.dictionary;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

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
