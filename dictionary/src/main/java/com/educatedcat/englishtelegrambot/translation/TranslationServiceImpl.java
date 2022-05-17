package com.educatedcat.englishtelegrambot.translation;

import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Slf4j
@Service
public class TranslationServiceImpl implements TranslationService {
	private final TranslationBuilder translationBuilder = new TranslationBuilder();
	private final TranslationRepositoryMap repositoryMap;
	
	@Autowired
	public TranslationServiceImpl(TranslationRepositoryMap repositoryMap) {
		this.repositoryMap = repositoryMap;
	}
	
	@Override
	@Transactional
	public Optional<? extends BaseTranslation> find(UUID uuid, Language language) {
		return repositoryMap.getRepository(language).findById(uuid);
	}
	
	@Override
	@Transactional
	public BaseTranslation save(TranslationDto dto) {
		return repositoryMap.getRepository(dto.language()).save(translationBuilder.buildTranslation(dto));
	}
	
	@Override
	@Transactional
	public void update(UUID uuid, Language language, TranslationDto dto) {
		repositoryMap.getRepository(language).findById(uuid)
		             .ifPresentOrElse(translation -> translation.merge(dto),
		                              () -> log.error("Translation not found, language={}, id={}", dto.language(),
		                                              dto.id()));
	}
}
