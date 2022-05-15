package com.educatedcat.englishtelegrambot.word;

import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Slf4j
@Service
public class WordServiceImpl implements WordService {
	private final WordRepository wordRepository;
	
	@Autowired
	public WordServiceImpl(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}
	
	@Override
	@Transactional
	public Optional<Word> find(UUID uuid) {
		return wordRepository.findById(uuid);
	}
	
	@Override
	@Transactional
	public Word save(WordDto dto) {
		return wordRepository.save(new Word(dto));
	}
	
	@Override
	@Transactional
	public void update(UUID uuid, WordDto dto) {
		wordRepository.findById(uuid)
		              .ifPresentOrElse(word -> word.merge(dto),
		                               () -> log.error("Word not found, id={}", dto.id()));
	}
}
