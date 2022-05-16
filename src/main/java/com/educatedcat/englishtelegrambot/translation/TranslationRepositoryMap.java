package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.translation.ru.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Need to get an entity repository by {@link Language}
 */
@Component
public class TranslationRepositoryMap {
	private final EnumMap<Language, ? super TranslationRepository<? extends BaseTranslation>> repositories;
	
	@Autowired
	public TranslationRepositoryMap(RuTranslationRepository ruTranslationRepository) {
		repositories = new EnumMap<>(Language.class) {{
			put(Language.RU, ruTranslationRepository);
		}};
	}
	
	@SuppressWarnings("unchecked")
	public TranslationRepository<? super BaseTranslation> getRepository(Language language) {
		return (TranslationRepository<? super BaseTranslation>) repositories.get(language);
	}
}
