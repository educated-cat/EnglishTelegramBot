package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;

import java.util.*;

public interface UserService {
	Optional<User> findById(Long id);
	
	void createUser(Long id, MenuButtonType buttonType, UUID buttonTypeId);
}
