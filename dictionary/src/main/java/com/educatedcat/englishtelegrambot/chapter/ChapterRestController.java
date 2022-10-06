package com.educatedcat.englishtelegrambot.chapter;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterRestController {
	private final ChapterService chapterService;
	
	@GetMapping("/{courseId}")
	public List<ChapterDto> findAll(@PathVariable UUID courseId) {
		return chapterService.findAllByCourseId(courseId)
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
}
