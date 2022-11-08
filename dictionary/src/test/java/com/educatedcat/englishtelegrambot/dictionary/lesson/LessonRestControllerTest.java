package com.educatedcat.englishtelegrambot.dictionary.lesson;

import com.educatedcat.englishtelegrambot.dictionary.*;
import com.fasterxml.jackson.core.type.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LessonRestControllerTest extends CustomMvcTest {
	@SuppressWarnings("rawtypes")
	private static final PostgreSQLContainer postgresContainer = new PostgreSQLContainer(
			DockerImageName.parse("postgres:15.0"));
	
	@BeforeAll
	public static void beforeAll() {
		postgresContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		postgresContainer.stop();
	}
	
	@Test
	@SneakyThrows
	void findAll() {
		List<LessonDto> lessons = performRequest(get("/api/lessons/" + UUID.randomUUID()),
		                                         new TypeReference<List<LessonDto>>() {
		                                         }).andExpect(status().isOk())
		                                           .andReturnDto();
		
		assertEquals(0, lessons.size());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}
}
