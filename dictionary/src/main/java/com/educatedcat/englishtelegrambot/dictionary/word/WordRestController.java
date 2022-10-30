package com.educatedcat.englishtelegrambot.dictionary.word;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/words")
public class WordRestController {
	private final WordService wordService;
	
	@GetMapping("/{uuid}")
	public WordDto findById(@PathVariable UUID uuid) {
		return wordService.find(uuid)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@GetMapping("/first/{lessonId}")
	public WordDto findFirstInLessonByLessonId(@PathVariable UUID lessonId) {
		return wordService.findFirstInLessonByLessonId(lessonId)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@GetMapping("/next/{previousWordId}")
	public WordDto findNext(@PathVariable UUID previousWordId) {
		return wordService.findNext(previousWordId)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@GetMapping("/by-lesson/{lessonId}")
	public List<WordDto> findAllByLessonId(@PathVariable UUID lessonId) {
		return wordService.findAllByLessonId(lessonId)
		                  .stream()
		                  .map(Word::toDto)
		                  .toList();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WordDto create(@RequestBody WordDto dto) {
		return wordService.save(dto).toDto();
	}
	
	@PutMapping("/{uuid}")
	public void update(@PathVariable UUID uuid, @RequestBody WordDto dto) {
		wordService.update(uuid, dto);
	}
}
