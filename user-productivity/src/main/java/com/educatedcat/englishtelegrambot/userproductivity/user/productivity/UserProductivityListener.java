package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.word.*;
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
	public void updateUserProductivity(WordProductivityDto dto) {
		userProductivityFacade.updateUserProductivity(dto);
	}
}
