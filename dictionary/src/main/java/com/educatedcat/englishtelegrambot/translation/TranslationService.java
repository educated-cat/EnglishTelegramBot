package com.educatedcat.englishtelegrambot.translation;

import com.educatedcat.englishtelegrambot.word.*;

public interface TranslationService {
	<T extends AbstractTranslation> T save(WordDto dto);
}
