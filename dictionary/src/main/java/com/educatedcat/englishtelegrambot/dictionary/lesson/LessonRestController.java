package com.educatedcat.englishtelegrambot.dictionary.lesson;

import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonRestController {
	private final LessonService lessonService;
	
	@ContinueSpan(log = "Find lessons in chapter by ID")
	@GetMapping("/{id}")
	public List<LessonDto> findAllInChapterByLessonId(@PathVariable UUID id) {
		return lessonService.findLessonsById(id)
		                    .stream()
		                    .map(Lesson::toDto)
		                    .toList();
	}
	
	@ContinueSpan(log = "Find lessons in chapter by chapter ID")
	@GetMapping("/by-chapter/{chapterId}")
	public List<LessonDto> findAllInChapter(@PathVariable UUID chapterId) {
		return lessonService.findAllByChapterId(chapterId)
		                    .stream()
		                    .map(Lesson::toDto)
		                    .toList();
	}
}
