package com.educatedcat.englishtelegrambot.lesson;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonRestController {
	private final LessonService lessonService;
	
	@GetMapping
	public List<LessonDto> findAll() {
		return lessonService.findAll()
		                    .stream()
		                    .map(Lesson::toDto)
		                    .toList();
	}
}
