package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.user.productivity.WordProductivityDto;
import com.educatedcat.englishtelegrambot.dictionary.word.productivity.WordProductivityService;
import io.micrometer.tracing.annotation.ContinueSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/words")
public class WordRestController {
	private final WordService wordService;
	private final WordProductivityService wordProductivityService;
	
	@ContinueSpan(log = "Find word by ID")
	@GetMapping("/{id}")
	public WordDto findById(@PathVariable long id) {
		return wordService.find(id)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@ContinueSpan(log = "Find first word in lesson")
	@GetMapping("/first/{lessonId}")
	public WordDto findFirstInLessonByLessonId(@PathVariable long lessonId) {
		return wordService.findFirstInLessonByLessonId(lessonId)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@ContinueSpan(log = "Find next word in lesson")
	@GetMapping("/next/{previousWordId}")
	public WordDto findNext(@PathVariable long previousWordId) {
		return wordService.findNext(previousWordId)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@ContinueSpan(log = "Find all words in lesson by lesson ID")
	@GetMapping("/by-lesson/{lessonId}")
	public List<WordDto> findAllByLessonId(@PathVariable long lessonId) {
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
	@PutMapping("/{id}")
	public void update(@PathVariable long id, @RequestBody WordDto dto) {
		wordService.update(id, dto);
	}
}
