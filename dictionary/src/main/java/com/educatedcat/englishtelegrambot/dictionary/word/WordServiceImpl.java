package com.educatedcat.englishtelegrambot.dictionary.word;

import com.educatedcat.englishtelegrambot.dictionary.translation.AbstractTranslation;
import com.educatedcat.englishtelegrambot.dictionary.translation.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
	private final WordRepository wordRepository;
	private final TranslationService translationService;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Word> find(long id) {
		return wordRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Word> findAllByLessonId(long lessonId) {
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
	public Optional<Word> findFirstInLessonByLessonId(long lessonId) {
		return wordRepository.findFirstByLessonsId(lessonId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Word> findNext(long previousWordId) {
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
	public void update(long id, WordDto dto) {
		wordRepository.findById(id)
		              .map(word -> {
			              word.merge(dto);
			              return word;
		              })
		              .orElseThrow();
	}
}
