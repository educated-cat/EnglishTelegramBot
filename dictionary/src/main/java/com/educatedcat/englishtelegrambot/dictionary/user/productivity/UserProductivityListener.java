package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.word.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserProductivityListener {
	private final UserProductivityFacade userProductivityFacade;
	
	@NewSpan("Update user productivity")
	@KafkaListener(topics = "${kafka.topic.name}")
	public void updateUserProductivity(UpdateWordProductivityDto dto) {
		userProductivityFacade.updateUserProductivity(dto);
	}
}
