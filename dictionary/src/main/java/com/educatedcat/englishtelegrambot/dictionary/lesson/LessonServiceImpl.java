package com.educatedcat.englishtelegrambot.dictionary.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
	private final LessonRepository lessonRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findAllInChapterByLessonId(long id) {
		return lessonRepository.findAllInChapterByLesson_Id(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesson> findAllByChapterId(long chapterId) {
		return lessonRepository.findAllByChapter_Id(chapterId);
	}
}
