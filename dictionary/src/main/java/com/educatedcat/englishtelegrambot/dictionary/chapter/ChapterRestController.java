package com.educatedcat.englishtelegrambot.dictionary.chapter;

import io.micrometer.tracing.annotation.ContinueSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterRestController {
	private final ChapterService chapterService;
	
	@ContinueSpan(log = "Find chapters in course by chapter ID")
	@GetMapping("/{id}")
	public List<ChapterDto> findAllInCourseByChapterId(@PathVariable long id) {
		return chapterService.findAllInCourseByChapterId(id)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
	
	@ContinueSpan(log = "Find chapters in course by course ID")
	@GetMapping("/by-course/{courseId}")
	public List<ChapterDto> findAllInCourse(@PathVariable long courseId) {
		return chapterService.findAllByCourseId(courseId)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
	
	@ContinueSpan(log = "Find chapters in course by lesson ID")
	@GetMapping("/by-lesson/{lessonId}")
	public List<ChapterDto> findAllChaptersByLessonId(@PathVariable long lessonId) {
		return chapterService.findAllInCourseByLessonId(lessonId)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
}
