package com.educatedcat.englishtelegrambot.chapter;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterRestController {
	private final ChapterService chapterService;
	
	@GetMapping
	public List<ChapterDto> findAll() {
		return chapterService.findAll()
		                     .stream()
		                     .map(Chapter::toDto)
		                     .toList();
	}
}
