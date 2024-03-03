package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
