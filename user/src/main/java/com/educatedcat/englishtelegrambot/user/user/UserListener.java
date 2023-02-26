package com.educatedcat.englishtelegrambot.user.user;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

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
