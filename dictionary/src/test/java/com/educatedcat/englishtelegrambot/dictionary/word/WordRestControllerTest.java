package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.*;
import com.educatedcat.englishtelegrambot.dictionary.translation.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class WordRestControllerTest extends CustomMvcTest {
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
	void findById() {
		WordDto dto = new WordDto(null, null, "cat", "caat", "", Language.RUS);
		
		WordDto created = performRequest(post("/api/words"), dto, WordDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
		
		created = performRequest(get("/api/words/{uuid}", created.id()), WordDto.class)
				.andExpect(status().isOk())
				.andReturnDto();
		
		assertNotNull(created.id());
		assertEquals(dto.name(), created.name());
		assertEquals(dto.transcription(), created.transcription());
	}
	
	@Test
	@SneakyThrows
	void create() {
		WordDto dto = new WordDto(null, null, "cat", "caat", "", Language.RUS);
		
		WordDto created = performRequest(post("/api/words"), dto, WordDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
		
		assertNotNull(created.id());
		assertEquals(dto.name(), created.name());
		assertEquals(dto.transcription(), created.transcription());
	}
	
	@Test
	@SneakyThrows
	void update() {
		WordDto dto = new WordDto(null, null, "cat", "caat", "", Language.RUS);
		
		WordDto created = performRequest(post("/api/words"), dto, WordDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
		
		dto = new WordDto(null, null, "dog", "dag", "", Language.RUS);
		
		performRequest(put("/api/words/{uuid}", created.id()), dto)
				.andExpect(status().isOk());
		
		WordDto updated = performRequest(get("/api/words/{uuid}", created.id()), WordDto.class)
				.andExpect(status().isOk())
				.andReturnDto();
		
		assertNotNull(updated.id());
		assertEquals(dto.name(), updated.name());
		assertEquals(dto.transcription(), updated.transcription());
	}
	
	@DynamicPropertySource
	private static void updateKafkaBootstrapServer(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresContainer::getUsername);
		registry.add("spring.datasource.password", postgresContainer::getPassword);
	}
}
