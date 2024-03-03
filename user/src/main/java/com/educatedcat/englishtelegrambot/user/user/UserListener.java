package com.educatedcat.englishtelegrambot.user.user;

import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserListener {
	private final UserService userService;
	
	@NewSpan("Update user state")
	@KafkaListener(topics = {"${kafka.topic.name}"})
	public void saveUserState(UserDto userDto) {
		if (userDto == null) {
			log.warn("Message is null: {}", UserDto.class.getSimpleName());
			return;
		}
		userService.saveOrUpdate(userDto);
	}
	
}
