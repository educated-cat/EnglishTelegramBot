package com.educatedcat.englishtelegrambot.bot.user;

import com.educatedcat.englishtelegrambot.bot.button.*;

import java.util.*;

public interface UserService {
	void saveOrUpdate(Long id, MenuButtonType buttonType, UUID buttonTypeId);
}
