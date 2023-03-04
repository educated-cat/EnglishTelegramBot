package com.educatedcat.englishtelegrambot.dictionary.lesson;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
	private final LessonRepository lessonRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findAllInChapterByLessonId(UUID id) {
		return lessonRepository.findAllInChapterByLesson_Id(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findAllByChapterId(UUID chapterId) {
		return lessonRepository.findAllByChapter_Id(chapterId);
	}
}
