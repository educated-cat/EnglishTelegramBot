package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.kafka.*;
import lombok.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final KafkaTemplate<Long, UserDto> kafkaTemplate;
	private final KafkaProperties kafkaProperties;
	
	@Override
	public void saveOrUpdate(UserDto userDto) {
		kafkaTemplate.send(kafkaProperties.getUser().getTopic().getName(), userDto.id(), userDto);
	}
}
