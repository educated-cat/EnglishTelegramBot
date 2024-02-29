package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.CustomKafkaProperties;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProductivityServiceImpl implements UserProductivityService {
	private final KafkaTemplate<Long, UserProductivityDto> kafkaTemplate;
	private final CustomKafkaProperties customKafkaProperties;
	
	@NewSpan("Update user productivity")
	@Override
	public void updateUserProductivity(UserProductivityDto dto) {
		kafkaTemplate.send(customKafkaProperties.getProductivity().getTopic().getName(), dto.userId(), dto);
	}
}
