package com.educatedcat.englishtelegrambot.user.user;

import lombok.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserListener {
	private final UserService userService;
	
	@KafkaListener(topics = {"${spring,kafka.name}"})
	public void saveUserState(UserDto userDto) {
		userService.saveOrUpdate(userDto);
	}
	
}
