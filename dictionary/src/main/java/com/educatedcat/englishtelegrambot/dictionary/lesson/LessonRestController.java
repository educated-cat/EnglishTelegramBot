package com.educatedcat.englishtelegrambot.dictionary.lesson;

import io.micrometer.tracing.annotation.ContinueSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonRestController {
	private final LessonService lessonService;
	
	@ContinueSpan(log = "Find lessons in chapter by lesson ID")
	@GetMapping("/{id}")
	public List<LessonDto> findAllInChapterByLessonId(@PathVariable long id) {
		return lessonService.findAllInChapterByLessonId(id)
		                    .stream()
		                    .map(Lesson::toDto)
		                    .toList();
	}
	
	@ContinueSpan(log = "Find lessons in chapter by chapter ID")
	@GetMapping("/by-chapter/{chapterId}")
	public List<LessonDto> findAllInChapter(@PathVariable long chapterId) {
		return lessonService.findAllByChapterId(chapterId)
		                    .stream()
		                    .map(Lesson::toDto)
		                    .toList();
	}
}
