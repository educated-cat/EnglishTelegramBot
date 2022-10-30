package com.educatedcat.englishtelegrambot.dictionary.translation;

import com.educatedcat.englishtelegrambot.dictionary.word.*;

public interface TranslationService {
	<T extends AbstractTranslation> T save(WordDto dto);
}
