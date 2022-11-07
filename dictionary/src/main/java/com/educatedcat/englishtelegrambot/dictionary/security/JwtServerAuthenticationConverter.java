package com.educatedcat.englishtelegrambot.dictionary.security;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.server.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;
import reactor.core.publisher.*;

@Component
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {
	@Override
	public Mono<Authentication> convert(ServerWebExchange exchange) {
		return Mono.justOrEmpty(exchange)
		           .flatMap(serverWebExchange -> Mono.justOrEmpty(
				           serverWebExchange.getRequest().getCookies().get("X-Auth")))
		           .filter(httpCookies -> !httpCookies.isEmpty())
		           .map(httpCookies -> httpCookies.get(0).getValue())
		           .map(s -> new UsernamePasswordAuthenticationToken(s, s));
	}
}
