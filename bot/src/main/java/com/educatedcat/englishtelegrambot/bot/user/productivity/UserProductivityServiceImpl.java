package com.educatedcat.englishtelegrambot.bot.user.productivity;

import com.educatedcat.englishtelegrambot.bot.kafka.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserProductivityServiceImpl implements UserProductivityService {
	private final KafkaTemplate<Long, UserProductivityDto> kafkaTemplate;
	private final KafkaProperties kafkaProperties;
	
	@NewSpan("Update user productivity")
	@Override
	public void updateUserProductivity(UserProductivityDto dto) {
		kafkaTemplate.send(kafkaProperties.getProductivity().getTopic().getName(), dto.userId(), dto);
	}
}
