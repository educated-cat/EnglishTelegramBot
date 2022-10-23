package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;

import java.util.*;

public record KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, UUID id, String name,
                            MenuButtonType idType) {
	public KeyboardEntry(MenuButtonType buttonType, String name) {
		this(buttonType, ActionButtonType.NEXT, null, name, buttonType);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, UUID id, String name) {
		this(buttonType, ActionButtonType.NEXT, id, name, buttonType);
	}
}
