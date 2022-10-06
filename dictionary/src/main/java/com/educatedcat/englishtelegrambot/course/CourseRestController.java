package com.educatedcat.englishtelegrambot.course;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseRestController {
	private final CourseService courseService;
	
	@GetMapping
	public List<CourseDto> findAll() {
		return courseService.findAll()
		                    .stream()
		                    .map(Course::toDto)
		                    .toList();
	}
}
