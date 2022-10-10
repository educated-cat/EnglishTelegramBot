package com.educatedcat.englishtelegrambot.bot.user.state;

import com.educatedcat.englishtelegrambot.bot.button.*;

import java.util.*;

public interface UserStateService {
	void updateState(Long id, MenuButtonType buttonType, UUID buttonTypeId);
}
