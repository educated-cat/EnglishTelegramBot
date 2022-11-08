package com.educatedcat.englishtelegrambot.dictionary.chapter;

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

class ChapterRestControllerTest extends CustomMvcTest {
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
	void findAllInCourseByChapterId() {
		List<ChapterDto> chapters = performRequest(get("/api/chapters/" + UUID.randomUUID()),
		                                           new TypeReference<List<ChapterDto>>() {
		                                           }).andExpect(status().isOk())
		                                             .andReturnDto();
		
		assertEquals(0, chapters.size());
	}
	
	@Test
	@SneakyThrows
	void findAllInCourse() {
		List<ChapterDto> chapters = performRequest(get("/api/chapters/by-course/" + UUID.randomUUID()),
		                                           new TypeReference<List<ChapterDto>>() {
		                                           }).andExpect(status().isOk())
		                                             .andReturnDto();
		
		assertEquals(0, chapters.size());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}
}
