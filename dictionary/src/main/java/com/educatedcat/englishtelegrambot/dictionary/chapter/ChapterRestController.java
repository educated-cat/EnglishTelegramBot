package com.educatedcat.englishtelegrambot.dictionary.chapter;

import io.micrometer.tracing.annotation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterRestController {
	private final ChapterService chapterService;
	
	@ContinueSpan(log = "Find chapters in course by chapter ID")
	@GetMapping("/{id}")
	public List<ChapterDto> findAllInCourseByChapterId(@PathVariable UUID id) {
		return chapterService.findAllInCourseByChapterId(id)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
	
	@ContinueSpan(log = "Find chapters in course by course ID")
	@GetMapping("/by-course/{courseId}")
	public List<ChapterDto> findAllInCourse(@PathVariable UUID courseId) {
		return chapterService.findAllByCourseId(courseId)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
	
	@ContinueSpan(log = "Find chapters in course by lesson ID")
	@GetMapping("/by-lesson/{lessonId}")
	public List<ChapterDto> findAllChaptersByLessonId(@PathVariable UUID lessonId) {
		return chapterService.findAllInCourseByLessonId(lessonId)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
}
