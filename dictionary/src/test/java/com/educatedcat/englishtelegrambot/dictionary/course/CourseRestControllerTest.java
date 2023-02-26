package com.educatedcat.englishtelegrambot.dictionary.course;

import com.educatedcat.englishtelegrambot.dictionary.*;
import com.fasterxml.jackson.core.type.*;
import lombok.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CourseRestControllerTest extends CustomMvcTest {
	@Test
	@SneakyThrows
	void findAll() {
		List<CourseDto> courses = performRequest(get("/api/courses"), new TypeReference<List<CourseDto>>() {
		}).andExpect(status().isOk())
		  .andReturnDto();
		
		assertEquals(0, courses.size());
	}
	
}
