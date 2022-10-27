package com.educatedcat.englishtelegrambot.dictionary.lesson;

import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
	private final LessonRepository lessonRepository;
	
	@Override
	public List<Lesson> findLessonsById(UUID id) {
		return lessonRepository.findAllInChapterByLesson_Id(id);
	}
	
	@Override
	public List<Lesson> findAllByChapterId(UUID chapterId) {
		return lessonRepository.findAllByChapter_Id(chapterId);
	}
}
