package com.educatedcat.englishtelegrambot.botreceiver.bot;

import io.netty.channel.ChannelOption;
import io.netty.resolver.DefaultAddressResolverGroup;
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
public class TelegramBotConfig {
	@Bean
	public WebClient botWebClient(TelegramBotProperties botProperties) {
		final var httpClient = HttpClient.create()
		                                 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
		                                 .resolver(DefaultAddressResolverGroup.INSTANCE)
		                                 .responseTimeout(Duration.ofSeconds(5));
		return WebClient.builder()
		                .clientConnector(new ReactorClientHttpConnector(httpClient))
		                .defaultHeaders(httpHeaders ->
				                                httpHeaders.add(HttpHeaders.CONTENT_TYPE,
				                                                new MediaType(MediaType.APPLICATION_JSON,
				                                                              StandardCharsets.UTF_8).toString()))
		                .baseUrl(botProperties.getFullApiUrl())
		                .build();
	}
}
