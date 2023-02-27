package com.educatedcat.englishtelegrambot.dictionary.chapter;

import lombok.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"chapters"})
public class ChapterServiceImpl implements ChapterService {
	private final ChapterRepository chapterRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Chapter> findChaptersById(UUID id) {
		return chapterRepository.findAllInCourseByChapter_Id(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Chapter> findAllByCourseId(UUID courseId) {
		return chapterRepository.findAllByCourse_Id(courseId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Chapter> findAllInCourseByLessonId(UUID lessonId) {
		return chapterRepository.findAllInCourseByLessonId(lessonId);
	}
}
