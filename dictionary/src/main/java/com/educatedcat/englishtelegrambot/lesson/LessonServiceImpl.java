package com.educatedcat.englishtelegrambot.lesson;

import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
	private final LessonRepository lessonRepository;
	
	@Override
	public List<Lesson> findAll() {
		return lessonRepository.findAll();
	}
}
