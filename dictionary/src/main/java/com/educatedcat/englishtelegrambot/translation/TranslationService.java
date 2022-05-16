package com.educatedcat.englishtelegrambot.translation;

import java.util.*;

public interface TranslationService {
	Optional<? extends BaseTranslation> find(UUID uuid, Language language);
	
	BaseTranslation save(TranslationDto dto);
	
	void update(UUID uuid, Language language, TranslationDto dto);
}
