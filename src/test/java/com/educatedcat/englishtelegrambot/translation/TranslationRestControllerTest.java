package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.*;
import com.educatedcat.englishtelegrambot.word.*;
import lombok.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TranslationRestControllerTest extends CustomMvcTest {
	
	@Test
	@SneakyThrows
	void findById() {
		WordDto word = createWord("cat", "caat");
		TranslationDto dto = new TranslationDto(word.id(), "Кот", Language.RU);
		
		TranslationDto created = performRequest(post("/api/translations"), dto, TranslationDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
		
		created = performRequest(get("/api/translations/{uuid}/{language}", created.id(), created.language()),
		                         TranslationDto.class)
				.andExpect(status().isOk())
				.andReturnDto();
		
		assertEquals(dto.id(), created.id());
		assertEquals(dto.translation(), created.translation());
		assertEquals(dto.language(), created.language());
	}
	
	@Test
	@SneakyThrows
	void create() {
		WordDto word = createWord("cat", "caat");
		TranslationDto dto = new TranslationDto(word.id(), "Кот", Language.RU);
		
		TranslationDto created = performRequest(post("/api/translations"), dto, TranslationDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
		
		assertNotNull(created.id());
		assertEquals(dto.translation(), created.translation());
		assertEquals(dto.language(), created.language());
	}
	
	@Test
	@SneakyThrows
	void update() {
		WordDto word = createWord("cat", "caat");
		TranslationDto dto = new TranslationDto(word.id(), "Кот", Language.RU);
		
		TranslationDto created = performRequest(post("/api/translations"), dto, TranslationDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
		
		dto = new TranslationDto(word.id(), "Собака", Language.RU);
		
		performRequest(put("/api/translations/{uuid}/{language}", created.id(), created.language()), dto)
				.andExpect(status().isOk());
		
		TranslationDto updated = performRequest(
				get("/api/translations/{uuid}/{language}", created.id(), created.language()),
				TranslationDto.class)
				.andExpect(status().isOk())
				.andReturnDto();
		
		assertEquals(dto.id(), updated.id());
		assertEquals(dto.translation(), updated.translation());
		assertEquals(dto.language(), updated.language());
	}
	
	@SneakyThrows
	private WordDto createWord(String name, String transcription) {
		WordDto dto = new WordDto(name, transcription);
		
		return performRequest(post("/api/words"), dto, WordDto.class)
				.andExpect(status().isCreated())
				.andReturnDto();
	}
}