package com.educatedcat.englishtelegrambot.dictionary.security;

import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
	private final JwtSigner jwtSigner;
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		return Mono.just(authentication)
		           .map(auth -> jwtSigner.validateJwt((String) auth.getCredentials()))
		           .onErrorResume(auth -> Mono.empty())
		           .map(jws -> new UsernamePasswordAuthenticationToken(
				           jws.getBody().getSubject(), authentication.getCredentials(),
				           List.of(new SimpleGrantedAuthority("ROLE_DICTIONARY"))));
	}
}
