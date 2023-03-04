package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProductivityListener {
	private final UserProductivityFacade userProductivityFacade;
	
	@NewSpan("Update user productivity")
	@KafkaListener(topics = "${kafka.topic.name}")
	public void updateUserProductivity(@Payload(required = false) UpdateWordProductivityDto dto) {
		if (dto == null) {
			log.warn("Message is null: {}", UpdateWordProductivityDto.class.getSimpleName());
			return;
		}
		userProductivityFacade.updateUserProductivity(dto);
	}
}
