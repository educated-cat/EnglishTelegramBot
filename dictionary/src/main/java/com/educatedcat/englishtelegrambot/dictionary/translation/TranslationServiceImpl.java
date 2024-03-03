package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.WordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {
	private final EnumMap<Language, TranslationRepository> translationRepositories;
	private final TranslationBuilder translationBuilder;
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public <T extends AbstractTranslation> T save(WordDto dto) {
		return (T) translationRepositories.get(dto.language()).save(translationBuilder.buildTranslation(dto));
	}
}
