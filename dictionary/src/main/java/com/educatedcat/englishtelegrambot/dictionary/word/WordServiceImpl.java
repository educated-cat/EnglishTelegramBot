package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
	private final WordRepository wordRepository;
	private final TranslationService translationService;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Word> find(UUID uuid) {
		return wordRepository.findById(uuid);
	}
	
	@Override
	@Transactional(readOnly = true)
	@Cacheable({"words"})
	public List<Word> findAllByLessonId(UUID lessonId) {
		return wordRepository.findAllByLessons_Id(lessonId);
	}
	
	@Override
	@Transactional
	public Word save(WordDto dto) {
		final AbstractTranslation translation = translationService.save(dto);
		return wordRepository.save(new Word(dto, translation));
	}
	
	@Override
	@Transactional(readOnly = true)
	@Cacheable({"words"})
	public Optional<Word> findFirstInLessonByLessonId(UUID lessonId) {
		return wordRepository.findFirstByLessonsId(lessonId);
	}
	
	@Override
	@Transactional(readOnly = true)
	@Cacheable({"words"})
	public Optional<Word> findNext(UUID previousWordId) {
		AtomicReference<Optional<Word>> res = new AtomicReference<>(Optional.empty());
		wordRepository.findById(previousWordId)
		              .ifPresent(word -> {
			              final Pageable pageable = PageRequest.of(0, 1, Sort.Direction.ASC, "index");
			              List<Word> nextWords = wordRepository.findNext(word.getIndex(), pageable).getContent();
			              if (nextWords.isEmpty()) {
				              throw new NoMoreWordsException();
			              } else {
				              res.set(Optional.of(nextWords.get(0)));
			              }
		              });
		return res.get();
	}
	
	@Override
	@Transactional
	public void update(UUID uuid, WordDto dto) {
		wordRepository.findById(uuid)
		              .ifPresentOrElse(word -> word.merge(dto),
		                               () -> log.error("Word not found, id={}", dto.id()));
	}
}
