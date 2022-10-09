package com.educatedcat.englishtelegrambot.chapter;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterRestController {
	private final ChapterService chapterService;
	
	@GetMapping("/{id}")
	public List<ChapterDto> findAllInCourseByChapterId(@PathVariable UUID id) {
		return chapterService.findChaptersById(id)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
	
	@GetMapping("/by-course/{courseId}")
	public List<ChapterDto> findAllInCourse(@PathVariable UUID courseId) {
		return chapterService.findAllByCourseId(courseId)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
}
