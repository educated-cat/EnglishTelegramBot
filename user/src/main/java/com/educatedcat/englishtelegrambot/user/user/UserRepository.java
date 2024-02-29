package com.educatedcat.englishtelegrambot.user.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {
	@Override
	@EntityGraph("User.states")
	Optional<User> findById(@NonNull Long id);
}
