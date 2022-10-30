package com.educatedcat.englishtelegrambot.dictionary.chapter;

import lombok.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"chapters"})
public class ChapterServiceImpl implements ChapterService {
	private final ChapterRepository chapterRepository;
	
	@Override
	public List<Chapter> findChaptersById(UUID id) {
		return chapterRepository.findAllInCourseByChapter_Id(id);
	}
	
	@Override
	public List<Chapter> findAllByCourseId(UUID courseId) {
		return chapterRepository.findAllByCourse_Id(courseId);
	}
	
	@Override
	public List<Chapter> findAllByLessonId(UUID lessonId) {
		return chapterRepository.findAllByLessonId(lessonId);
	}
}
