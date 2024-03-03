package com.educatedcat.englishtelegrambot.dictionary.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface TranslationRepository<T, ID> extends JpaRepository<T, ID> {
}
