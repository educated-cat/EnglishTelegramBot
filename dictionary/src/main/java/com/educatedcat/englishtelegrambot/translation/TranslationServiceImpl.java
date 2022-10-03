package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.word.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

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
