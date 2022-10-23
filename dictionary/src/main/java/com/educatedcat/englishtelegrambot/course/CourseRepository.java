package com.educatedcat.englishtelegrambot.course;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface CourseRepository extends JpaRepository<Course, UUID> {
}
