package com.educatedcat.englishtelegrambot.dictionary.course;

import lombok.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;
	
	@Override
	@Cacheable(cacheNames = {"courses"}, cacheManager = "courseCacheManager")
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
}
