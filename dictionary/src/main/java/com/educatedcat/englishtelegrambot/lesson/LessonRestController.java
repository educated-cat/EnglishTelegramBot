package com.educatedcat.englishtelegrambot.lesson;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonRestController {
	private final LessonService lessonService;
	
	@GetMapping("/{chapterId}")
	public List<LessonDto> findAll(@PathVariable UUID chapterId) {
		return lessonService.findAllByChapterId(chapterId)
		                    .stream()
		                    .map(Lesson::toDto)
		                    .toList();
	}
}
