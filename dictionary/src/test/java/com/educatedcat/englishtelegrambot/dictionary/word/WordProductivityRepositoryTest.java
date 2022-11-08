package com.educatedcat.englishtelegrambot.dictionary.word;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WordProductivityRepositoryTest {
	private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
			DockerImageName.parse("postgres:15.0"));
	
	@BeforeAll
	public static void beforeAll() {
		postgresContainer.start();
	}
	
	@AfterAll
	public static void afterAll() {
		postgresContainer.stop();
	}
	
	@Autowired
	private WordProductivityRepository wordProductivityRepository;
	
	@Test
	void save() {
		UUID wordId = UUID.randomUUID();
		WordProductivity productivity = new WordProductivity(1L, wordId);
		
		wordProductivityRepository.save(productivity);
		
		WordProductivity created = wordProductivityRepository.findById(1L).orElseThrow();
		assertEquals(1L, created.getUserId());
		assertEquals(wordId, created.getWordId());
		assertEquals(0, created.getProgress());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}
	
}
