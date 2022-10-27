package com.educatedcat.englishtelegrambot.dictionary.course;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface CourseRepository extends JpaRepository<Course, UUID> {
}
