package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;
import com.educatedcat.englishtelegrambot.bot.kafka.*;
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

@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class UserServiceTest {
	private static final KafkaContainer kafkaContainer = new KafkaContainer(
			DockerImageName.parse("confluentinc/cp-kafka:latest"));
	
	@BeforeAll
	public static void beforeAll() {
		kafkaContainer.start();
	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Autowired
	private KafkaConsumer<Long, UserDto> consumer;
	
	@Test
	@SneakyThrows
	void saveOrUpdate() {
		consumer.subscribe(List.of(kafkaProperties.getUser().getTopic().getName()));
		UserDto user = new UserDto(1L, MenuButtonType.START, UUID.randomUUID());
		
		userService.saveOrUpdate(user);
		
		await().atMost(Duration.ofSeconds(10)).until(() -> {
			var records = consumer.poll(Duration.ofMillis(100));
			if (records.isEmpty()) {
				return false;
			}
			consumer.close();
			
			assertEquals(1, records.count());
			records.forEach(r -> {
				assertEquals(kafkaProperties.getUser().getTopic().getName(), r.topic());
				assertEquals(user, r.value());
				assertEquals(user.id(), r.key());
			});
			return true;
		});
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}
}
