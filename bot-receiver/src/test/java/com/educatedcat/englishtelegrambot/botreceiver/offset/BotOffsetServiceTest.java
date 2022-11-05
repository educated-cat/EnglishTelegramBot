package com.educatedcat.englishtelegrambot.botreceiver.offset;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
class BotOffsetServiceTest {
	@SuppressWarnings("rawtypes")
	private static final PostgreSQLContainer postgresContainer = new PostgreSQLContainer(
			DockerImageName.parse("postgres:15.0"));
	
	@Autowired
	private BotOffsetService botOffsetService;
	
	@BeforeAll
	public static void beforeAll() {
		postgresContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		postgresContainer.stop();
	}
	
	@Test
	void getCurrentOffset() {
		BotOffset offset = botOffsetService.getCurrentOffset().orElseThrow();
		
		assertEquals(0, offset.getOffset());
	}
	
	@Test
	void updateCurrentOffset() {
		botOffsetService.updateCurrentOffset(12345L);
		
		BotOffset updatedOffset = botOffsetService.getCurrentOffset().orElseThrow();
		assertEquals(12345L, updatedOffset.getOffset());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}
}
