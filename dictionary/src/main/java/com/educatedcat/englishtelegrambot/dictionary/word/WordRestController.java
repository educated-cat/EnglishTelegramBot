package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.user.productivity.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/words")
public class WordRestController {
	private final WordService wordService;
	private final WordProductivityService wordProductivityService;
	
	@ContinueSpan(log = "Find word by ID")
	@GetMapping("/{uuid}")
	public WordDto findById(@PathVariable UUID uuid) {
		return wordService.find(uuid)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@ContinueSpan(log = "Find first word in lesson")
	@GetMapping("/first/{lessonId}")
	public WordDto findFirstInLessonByLessonId(@PathVariable UUID lessonId) {
		return wordService.findFirstInLessonByLessonId(lessonId)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@ContinueSpan(log = "Find next word in lesson")
	@GetMapping("/next/{previousWordId}")
	public WordDto findNext(@PathVariable UUID previousWordId) {
		return wordService.findNext(previousWordId)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@ContinueSpan(log = "Find all words in lesson by lesson ID")
	@GetMapping("/by-lesson/{lessonId}")
	public List<WordDto> findAllByLessonId(@PathVariable UUID lessonId) {
		return wordService.findAllByLessonId(lessonId)
		                  .stream()
		                  .map(Word::toDto)
		                  .toList();
	}
	
	@ContinueSpan(log = "Find word productivity by user id")
	@GetMapping("/productivity/{userId}")
	public WordProductivityDto findWordProductivityByUserId(@PathVariable long userId) {
		return wordProductivityService.getByUserId(userId);
	}
	
	@ContinueSpan(log = "Create word")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WordDto create(@RequestBody WordDto dto) {
		return wordService.save(dto).toDto();
	}
	
	@ContinueSpan(log = "Update word by ID")
	@PutMapping("/{uuid}")
	public void update(@PathVariable UUID uuid, @RequestBody WordDto dto) {
		wordService.update(uuid, dto);
	}
}
