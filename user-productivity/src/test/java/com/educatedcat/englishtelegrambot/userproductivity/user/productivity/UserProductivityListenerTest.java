package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.kafka.*;
import com.educatedcat.englishtelegrambot.userproductivity.word.*;
import org.junit.jupiter.api.*;
import org.mockito.exceptions.verification.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.kafka.core.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.time.*;
import java.util.*;

import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(UserProductivityFacade.class)
})
@SpringBootTest
class UserProductivityListenerTest {
	private static final KafkaContainer container = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:latest"));
	
	@BeforeAll
	public static void beforeAll() {
		container.start();
	}
	
	@Autowired
	private KafkaTemplate<Long, UserProductivityDto> kafkaTemplate;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Test
	void updateUserProductivity() {
		UserProductivityDto productivity = new UserProductivityDto(1L, UUID.randomUUID(), WordActionType.KNOW);
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), productivity.userId(), productivity);
		
		await().atMost(Duration.ofSeconds(10)).until(() -> {
			try {
				then(userProductivityFacade).should().updateUserProductivity(productivity);
			} catch (WantedButNotInvoked e) {
				return false;
			}
			return true;
		});
	}
}
