package com.educatedcat.englishtelegrambot.userstatistics.user;

import com.educatedcat.englishtelegrambot.userstatistics.kafka.*;
import com.educatedcat.englishtelegrambot.userstatistics.word.*;
import org.apache.kafka.clients.consumer.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.kafka.core.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.time.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(UserProductivityFacade.class)
})
@SpringBootTest
class UserProductivityListenerTest {
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
	private KafkaTemplate<Long, Long> kafkaTemplate;
	
	@Autowired
	private KafkaConsumer<Long, UserProductivityDto> userProductivityDtoKafkaConsumer;
	
	@Autowired
	private CustomKafkaProperties customKafkaProperties;
	
	@Autowired
	private UserProductivityFacade userProductivityFacade;
	
	@Test
	void getUserProductivity() {
		long userId = 123;
		UserProductivityDto userProductivity = new UserProductivityDto(new WordProductivityDto(10, 5, 85));
		given(userProductivityFacade.prepareUserProductivity(userId)).willReturn(userProductivity);
		userProductivityDtoKafkaConsumer.subscribe(List.of(customKafkaProperties.getTopic().getReplyName()));
		
		kafkaTemplate.send(customKafkaProperties.getTopic().getRequestName(), userId, userId);
		
		await().atMost(Duration.ofSeconds(10)).with().pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(userProductivityFacade).should().prepareUserProductivity(userId));
		
		await().atMost(Duration.ofSeconds(10)).until(() -> {
			var records = userProductivityDtoKafkaConsumer.poll(Duration.ofMillis(100));
			if (records.isEmpty()) {
				return false;
			}
			userProductivityDtoKafkaConsumer.close();
			
			assertThat(records.count()).isEqualTo(1);
			records.forEach(r -> {
				assertThat(r.topic()).isEqualTo(customKafkaProperties.getTopic().getReplyName());
				assertThat(r.value()).isEqualTo(userProductivity);
				assertThat(r.value()).isEqualTo(userProductivity);
			});
			return true;
		});
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}
	
}
