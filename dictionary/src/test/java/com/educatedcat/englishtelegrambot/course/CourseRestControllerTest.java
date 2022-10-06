package com.educatedcat.englishtelegrambot.course;

import com.educatedcat.englishtelegrambot.*;
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
		List<CourseDto> lessons = performRequest(get("/api/courses"), new TypeReference<List<CourseDto>>() {
		}).andExpect(status().isOk())
		  .andReturnDto();
		
		assertEquals(0, lessons.size());
	}
}