package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import lombok.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class UserProductivityListener {
	private final UserProductivityFacade userProductivityFacade;
	
	@KafkaListener(topics = "${kafka.topic.name}")
	public void updateUserProductivity(UserProductivityDto dto) {
		userProductivityFacade.updateUserProductivity(dto);
	}
}
