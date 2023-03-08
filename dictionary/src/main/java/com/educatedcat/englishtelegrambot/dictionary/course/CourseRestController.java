package com.educatedcat.englishtelegrambot.dictionary.course;

import io.micrometer.tracing.annotation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
