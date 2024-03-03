package com.educatedcat.englishtelegrambot.botreceiver.user;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.CustomKafkaProperties;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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
