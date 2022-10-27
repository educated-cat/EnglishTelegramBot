package com.educatedcat.englishtelegrambot.dictionary.translation;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

import java.util.*;

@NoRepositoryBean
interface TranslationRepository extends JpaRepository<AbstractTranslation, UUID> {
}
