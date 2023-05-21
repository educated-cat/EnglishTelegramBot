package com.educatedcat.englishtelegrambot.dictionary.user.productivity;

import com.educatedcat.englishtelegrambot.dictionary.kafka.*;
import com.educatedcat.englishtelegrambot.dictionary.word.*;
import com.educatedcat.englishtelegrambot.dictionary.word.productivity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.test.context.*;

import java.time.*;
import java.util.*;

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
				1L, UUID.randomUUID(), WordActionType.KNOW);
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
