package com.educatedcat.englishtelegrambot.dictionary;

import com.educatedcat.englishtelegrambot.dictionary.security.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.web.server.*;
import org.springframework.security.web.server.*;
import org.springframework.security.web.server.authentication.*;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
	                                                     JwtAuthenticationManager jwtAuthManager,
	                                                     JwtServerAuthenticationConverter jwtServerAuthConverter) {
		final var webFilter = new AuthenticationWebFilter(jwtAuthManager) {{
			setServerAuthenticationConverter(jwtServerAuthConverter);
		}};
		return http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
		           .addFilterAfter(webFilter, SecurityWebFiltersOrder.AUTHENTICATION)
		           .httpBasic()
		           .disable()
		           .csrf()
		           .disable()
		           .formLogin()
		           .disable()
		           .logout()
		           .disable()
		           .build();
	}
}
