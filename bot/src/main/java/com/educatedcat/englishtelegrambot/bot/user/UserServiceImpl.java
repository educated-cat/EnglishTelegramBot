package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	@Transactional
	public void createUser(Long id, MenuButtonType buttonType, UUID buttonTypeId) {
		userRepository.save(new User(id, buttonType, buttonTypeId));
	}
}
