package com.educatedcat.englishtelegrambot.lesson;

import java.util.*;

public interface LessonService {
	List<Lesson> findAllByChapterId(UUID chapterId);
}