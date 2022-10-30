package com.educatedcat.englishtelegrambot.dictionary;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.cache.*;
import org.springframework.cache.annotation.*;
import org.springframework.cache.caffeine.*;
import org.springframework.context.annotation.*;

import java.time.*;

@EnableCaching
@Configuration
public class CacheConfig {
	@Bean
	public CacheManager caffeineCacheManager() {
		return new CaffeineCacheManager() {{
			registerCustomCache("courses", Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(5)).build());
			registerCustomCache("chapters", Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(5)).build());
			registerCustomCache("lessons", Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(5)).build());
		}};
	}
}
