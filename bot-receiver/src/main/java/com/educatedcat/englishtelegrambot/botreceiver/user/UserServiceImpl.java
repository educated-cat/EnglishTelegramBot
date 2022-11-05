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
	private final KafkaProperties kafkaProperties;
	
	@NewSpan("Update user state")
	@Override
	public void saveOrUpdate(UserDto userDto) {
		kafkaTemplate.send(kafkaProperties.getUser().getTopic().getName(), userDto.id(), userDto);
	}
}
