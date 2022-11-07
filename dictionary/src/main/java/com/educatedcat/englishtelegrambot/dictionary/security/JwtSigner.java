package com.educatedcat.englishtelegrambot.dictionary.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import org.springframework.stereotype.*;

import java.security.*;
import java.time.*;
import java.util.*;

@Service
public class JwtSigner {
	private final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
	
	public String createJwt(Long userId) {
		final Instant instant = Instant.now();
		return Jwts.builder()
				.signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
				.setSubject(userId.toString())
				.setIssuer("identity")
				.setExpiration(Date.from(instant.plus(Duration.ofMinutes(15))))
				.setIssuedAt(Date.from(instant))
				.compact();
	}
	
	/**
	 * Проверить JWT там, где он будет выбрасывать исключения, не являясь допустимым.
	 */
	public Jws<Claims> validateJwt(String jwt) {
		return Jwts.parserBuilder().setSigningKey(keyPair.getPrivate())
		           .build()
		           .parseClaimsJws(jwt);
	}
}
