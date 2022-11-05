package com.educatedcat.englishtelegrambot.user.user;

import com.educatedcat.englishtelegrambot.user.button.*;
import com.educatedcat.englishtelegrambot.user.kafka.*;
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

import static org.awaitility.Awaitility.*;
import static org.mockito.BDDMockito.*;

@MockBeans({
		@MockBean(UserService.class)
})
@SpringBootTest
class UserListenerTest {
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
	private KafkaTemplate<Long, UserDto> kafkaTemplate;
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Autowired
	private UserService userService;
	
	@Test
	void saveUserState() {
		UserDto user = new UserDto(1L, MenuButtonType.START, UUID.randomUUID());
		
		kafkaTemplate.send(kafkaProperties.getTopic().getName(), user.id(), user);
		
		await().atMost(Duration.ofSeconds(10)).with().pollInterval(Duration.ofMillis(100))
		       .untilAsserted(() -> then(userService).should().saveOrUpdate(user));
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}
	
}
