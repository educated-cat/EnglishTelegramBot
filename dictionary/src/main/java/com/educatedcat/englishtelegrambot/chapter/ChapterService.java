package com.educatedcat.englishtelegrambot.chapter;

import java.util.*;

public interface ChapterService {
	List<Chapter> findAllByCourseId(UUID courseId);
}
