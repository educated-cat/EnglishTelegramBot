package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.kafka.KafkaProperties;
import com.educatedcat.englishtelegrambot.dictionary.word.UpdateWordProductivityDto;
import com.educatedcat.englishtelegrambot.dictionary.word.WordActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.Duration;

import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.reset;
import static org.mockito.BDDMockito.*;

@EmbeddedKafka
@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class UserProductivityListenerTest {
	@Autowired
	private KafkaTemplate<Long, UpdateWordProductivityDto> kafkaTemplate;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@MockBean
	private UserProductivityFacade userProductivityFacade;
	
	@SpyBean
	private UserProductivityListener userProductivityListener;
	
	@BeforeEach
	void beforeEach() {
		reset(userProductivityListener);
	}
	
	@Test
	void updateUserProductivity() {
		UpdateWordProductivityDto productivity = new UpdateWordProductivityDto(
				1L, 2, WordActionType.KNOW);
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), productivity.userId(), productivity);
		
		await().atMost(Duration.ofSeconds(10))
		       .with()
		       .pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(userProductivityFacade).should().updateUserProductivity(productivity));
	}
	
	@Test
	void unableToUpdateUserProductivityBecauseProductivityIsNull() {
		userProductivityListener.updateUserProductivity(null);
		
		await().during(Duration.ofMillis(200))
		       .and()
		       .atMost(Duration.ofSeconds(10))
		       .with()
		       .pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(userProductivityListener).should().updateUserProductivity(
				       nullable(UpdateWordProductivityDto.class)));
		then(userProductivityFacade).should(never()).updateUserProductivity(any(UpdateWordProductivityDto.class));
	}
}
