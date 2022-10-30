package com.educatedcat.englishtelegrambot.userproductivity.user.productivity;

import org.springframework.data.jpa.repository.*;

import java.util.*;

interface UserProductivityRepository extends JpaRepository<UserProductivity, Long> {
	Optional<UserProductivity> findByUserIdAndWordId(long userId, UUID wordId);
}
