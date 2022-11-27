package com.educatedcat.englishtelegrambot.botreceiver.user.productivity;

import com.educatedcat.englishtelegrambot.botreceiver.kafka.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;
import lombok.*;
import org.apache.kafka.clients.consumer.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.time.*;
import java.util.*;

import static org.awaitility.Awaitility.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
		"spring.main.lazy-initialization=true",
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class UserProductivityServiceTest {
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
	private UserProductivityService userProductivityService;
	
	@Autowired
	private CustomKafkaProperties customKafkaProperties;
	
	@Autowired
	private KafkaConsumer<Long, UserProductivityDto> consumer;
	
	@Test
	@SneakyThrows
	void updateUserProductivity() {
		consumer.subscribe(List.of(customKafkaProperties.getProductivity().getTopic().getName()));
		UserProductivityDto productivity = new UserProductivityDto(1L, UUID.randomUUID(), WordActionType.KNOW);
		
		userProductivityService.updateUserProductivity(productivity);
		
		await().atMost(Duration.ofSeconds(10)).until(() -> {
			var records = consumer.poll(Duration.ofMillis(100));
			if (records.isEmpty()) {
				return false;
			}
			consumer.close();
			
			assertEquals(1, records.count());
			records.forEach(r -> {
				assertEquals(customKafkaProperties.getProductivity().getTopic().getName(), r.topic());
				assertEquals(productivity, r.value());
				assertEquals(productivity.userId(), r.key());
			});
			return true;
		});
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}
	
}
