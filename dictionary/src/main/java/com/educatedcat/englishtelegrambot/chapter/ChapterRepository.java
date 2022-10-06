package com.educatedcat.englishtelegrambot.chapter;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface ChapterRepository extends JpaRepository<Chapter, UUID> {
	List<Chapter> findAllByCourse_Id(UUID courseId);
}
