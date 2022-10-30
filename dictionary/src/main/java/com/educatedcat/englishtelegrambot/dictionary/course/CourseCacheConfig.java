package com.educatedcat.englishtelegrambot.dictionary.course;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.cache.*;
import org.springframework.cache.caffeine.*;
import org.springframework.context.annotation.*;

import java.time.*;

@Configuration
public class CourseCacheConfig {
	@Bean
	public CacheManager courseCacheManager() {
		return new CaffeineCacheManager("courses") {{
			setCaffeine(caffeine());
		}};
	}
	
	private Caffeine<Object, Object> caffeine() {
		return Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(5));
	}
}
