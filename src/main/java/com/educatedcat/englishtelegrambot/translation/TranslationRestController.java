package com.educatedcat.englishtelegrambot.translation;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/translations")
public class TranslationRestController {
	private final TranslationService translationService;
	
	@Autowired
	public TranslationRestController(TranslationService translationService) {
		this.translationService = translationService;
	}
	
	@GetMapping(value = "/{uuid}/{language}")
	public TranslationDto findById(@PathVariable UUID uuid, @PathVariable Language language) {
		return translationService.find(uuid, language)
		                         .map(BaseTranslation::toDto)
		                         .orElseThrow();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TranslationDto create(@RequestBody TranslationDto dto) {
		final BaseTranslation translation = translationService.save(dto);
		return translation.toDto();
	}
	
	@PutMapping("/{uuid}/{language}")
	public void update(@PathVariable UUID uuid, @PathVariable Language language, @RequestBody TranslationDto dto) {
		translationService.update(uuid, language, dto);
	}
}
