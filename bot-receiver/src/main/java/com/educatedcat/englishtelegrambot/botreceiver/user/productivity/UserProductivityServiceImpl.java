package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.*;
import io.micrometer.tracing.annotation.*;
import lombok.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

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
