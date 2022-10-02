package com.educatedcat.englishtelegrambot.word;

import com.educatedcat.englishtelegrambot.translation.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
	private final WordRepository wordRepository;
	private final TranslationService translationService;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Word> find(UUID uuid) {
		return wordRepository.findById(uuid);
	}
	
	@Override
	@Transactional
	public Word save(WordDto dto) {
		final AbstractTranslation translation = translationService.save(dto);
		return wordRepository.save(new Word(dto, translation));
	}
	
	@Override
	@Transactional
	public void update(UUID uuid, WordDto dto) {
		wordRepository.findById(uuid)
		              .ifPresentOrElse(word -> word.merge(dto),
		                               () -> log.error("Word not found, id={}", dto.id()));
	}
}
