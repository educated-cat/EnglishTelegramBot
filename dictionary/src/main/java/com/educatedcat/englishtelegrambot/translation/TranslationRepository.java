package com.educatedcat.englishtelegrambot.translation;

import org.springframework.data.repository.*;

import java.util.*;

@NoRepositoryBean
public interface TranslationRepository<T extends BaseTranslation> extends CrudRepository<T, UUID> {
}