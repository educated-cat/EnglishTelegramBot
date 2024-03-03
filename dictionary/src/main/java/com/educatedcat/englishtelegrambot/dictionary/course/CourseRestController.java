package com.educatedcat.englishtelegrambot.dictionary.course;

import io.micrometer.tracing.annotation.ContinueSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseRestController {
	private final CourseService courseService;
	
	@ContinueSpan(log = "Find courses")
	@GetMapping
	public List<CourseDto> findAll() {
		return courseService.findAll()
		                    .stream()
		                    .map(Course::toDto)
		                    .toList();
	}
}
