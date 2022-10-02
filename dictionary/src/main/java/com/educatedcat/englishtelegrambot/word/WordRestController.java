package com.educatedcat.englishtelegrambot.word;

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
