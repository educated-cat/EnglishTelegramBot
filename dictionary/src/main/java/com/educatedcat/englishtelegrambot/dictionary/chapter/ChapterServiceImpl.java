package com.educatedcat.englishtelegrambot.dictionary.chapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
	private final ChapterRepository chapterRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Chapter> findAllInCourseByChapterId(long id) {
		return chapterRepository.findAllInCourseByChapter_Id(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Chapter> findAllByCourseId(long courseId) {
		return chapterRepository.findAllByCourse_Id(courseId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Chapter> findAllInCourseByLessonId(long lessonId) {
		return chapterRepository.findAllInCourseByLessonId(lessonId);
	}
}
