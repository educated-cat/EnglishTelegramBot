package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.kafka.*;
import lombok.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserProductivityServiceImpl implements UserProductivityService {
	private final KafkaTemplate<Long, UserProductivityDto> kafkaTemplate;
	private final KafkaProperties kafkaProperties;
	
	@Override
	public void updateUserProductivity(UserProductivityDto dto) {
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), dto.userId(), dto);
	}
}
