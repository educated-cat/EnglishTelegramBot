package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import com.educatedcat.englishtelegrambot.userproductivity.kafka.*;
import com.educatedcat.englishtelegrambot.userproductivity.word.*;
import org.junit.jupiter.api.*;
import org.mockito.exceptions.verification.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.kafka.core.*;
import org.springframework.test.context.*;
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
class WordProductivityListenerTest {
	private static final KafkaContainer kafkaContainer = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:latest"));
	
	@BeforeAll
	public static void beforeAll() {
		kafkaContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		kafkaContainer.stop();
	}
	
	@Autowired
	private KafkaTemplate<Long, WordProductivityDto> kafkaTemplate;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Test
	void updateUserProductivity() {
		WordProductivityDto productivity = new WordProductivityDto(1L, UUID.randomUUID(), WordActionType.KNOW);
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), productivity.userId(), productivity);
		
		await().atMost(Duration.ofSeconds(10)).with().pollInterval(Duration.ofMillis(100)).until(() -> {
			try {
				then(userProductivityFacade).should().updateUserProductivity(productivity);
			} catch (WantedButNotInvoked e) {
				return false;
			}
			return true;
		});
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}
	
}