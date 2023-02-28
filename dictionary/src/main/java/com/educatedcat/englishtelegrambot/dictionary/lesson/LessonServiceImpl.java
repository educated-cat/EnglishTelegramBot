package com.educatedcat.englishtelegrambot.dictionary.lesson;

import lombok.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"lessons"})
public class LessonServiceImpl implements LessonService {
	private final LessonRepository lessonRepository;
	
	@Override
	public List<Lesson> findAllInChapterByLessonId(UUID id) {
		return lessonRepository.findAllInChapterByLesson_Id(id);
	}
	
	@Override
	public List<Lesson> findAllByChapterId(UUID chapterId) {
		return lessonRepository.findAllByChapter_Id(chapterId);
	}
}
