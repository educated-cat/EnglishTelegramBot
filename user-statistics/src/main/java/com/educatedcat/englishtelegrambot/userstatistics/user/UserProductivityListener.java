package com.educatedcat.englishtelegrambot.userstatistics.user;

import lombok.*;
import org.springframework.kafka.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserProductivityListener {
	private final UserProductivityFacade userProductivityFacade;
	
	@SendTo("${kafka.topic.reply-name}")
	@KafkaListener(topics = "${kafka.topic.request-name}")
	public UserProductivityDto getUserProductivity(long userId) {
		return userProductivityFacade.prepareUserProductivity(userId);
	}
}
