package com.educatedcat.englishtelegrambot.botreceiver.user;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final KafkaTemplate<Long, UserDto> kafkaTemplate;
	private final CustomKafkaProperties customKafkaProperties;
	
	@NewSpan("Update user state")
	@Override
	public void saveOrUpdate(UserDto userDto) {
		kafkaTemplate.send(customKafkaProperties.getUser().getTopic().getName(), userDto.id(), userDto);
	}
}
