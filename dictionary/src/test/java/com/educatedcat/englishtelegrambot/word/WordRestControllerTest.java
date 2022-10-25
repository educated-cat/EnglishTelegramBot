package com.educatedcat.englishtelegrambot.word;

import com.educatedcat.englishtelegrambot.*;
import com.educatedcat.englishtelegrambot.translation.*;
import lombok.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class WordRestControllerTest extends CustomMvcTest {
	
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
}
