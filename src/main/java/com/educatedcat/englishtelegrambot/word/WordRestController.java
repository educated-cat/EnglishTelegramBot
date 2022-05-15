package com.educatedcat.englishtelegrambot.word;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/words")
public class WordRestController {
	private final WordService wordService;
	
	@Autowired
	public WordRestController(WordService wordService) {
		this.wordService = wordService;
	}
	
	@GetMapping("/{uuid}")
	public WordDto findById(@PathVariable UUID uuid) {
		return wordService.find(uuid)
		                  .map(Word::toDto)
		                  .orElseThrow();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WordDto create(@RequestBody WordDto dto) {
		final Word word = wordService.save(dto);
		return word.toDto();
	}
	
	@PutMapping("/{uuid}")
	public void update(@PathVariable UUID uuid, @RequestBody WordDto dto) {
		wordService.update(uuid, dto);
	}
}
