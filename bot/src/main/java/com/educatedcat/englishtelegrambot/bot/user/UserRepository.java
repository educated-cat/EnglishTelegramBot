package com.educatedcat.englishtelegrambot.bot.user;

import org.springframework.data.jpa.repository.*;
import org.springframework.lang.*;

import java.util.*;

interface UserRepository extends JpaRepository<User, Long> {
	@Override
	@EntityGraph("User.states")
	Optional<User> findById(@NonNull Long id);
}
