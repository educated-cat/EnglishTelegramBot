package com.educatedcat.englishtelegrambot.chapter;

import com.educatedcat.englishtelegrambot.*;
import com.fasterxml.jackson.core.type.*;
import lombok.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ChapterRestControllerTest extends CustomMvcTest {
	
	@Test
	@SneakyThrows
	void findAll() {
		List<ChapterDto> lessons = performRequest(get("/api/chapters/" + UUID.randomUUID()),
		                                          new TypeReference<List<ChapterDto>>() {
		                                          }).andExpect(status().isOk())
		                                            .andReturnDto();
		
		assertEquals(0, lessons.size());
	}
}