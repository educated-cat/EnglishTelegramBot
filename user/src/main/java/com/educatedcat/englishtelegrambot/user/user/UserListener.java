package com.educatedcat.englishtelegrambot.user.user;

import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserListener {
	private final UserService userService;
	
	@NewSpan("Update user state")
	@KafkaListener(topics = {"${kafka.topic.name}"})
	public void saveUserState(UserDto userDto) {
		userService.saveOrUpdate(userDto);
	}
	
}
