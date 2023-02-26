package com.educatedcat.englishtelegrambot.dictionary.lesson;

import com.educatedcat.englishtelegrambot.dictionary.*;
import com.fasterxml.jackson.core.type.*;
import lombok.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LessonRestControllerTest extends CustomMvcTest {
	@Test
	@SneakyThrows
	void findAll() {
		List<LessonDto> lessons = performRequest(get("/api/lessons/" + UUID.randomUUID()),
		                                         new TypeReference<List<LessonDto>>() {
		                                         }).andExpect(status().isOk())
		                                           .andReturnDto();
		
		assertEquals(0, lessons.size());
	}
}
