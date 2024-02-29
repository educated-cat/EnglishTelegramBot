package com.educatedcat.englishtelegrambot.botreceiver.keyboard;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.word.WordActionType;

public record KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, Long id, String name,
                            MenuButtonType idType, WordActionType wordActionType, Long lessonId) {
	public KeyboardEntry(MenuButtonType buttonType, String name) {
		this(buttonType, ActionButtonType.NEXT, null, name, buttonType, null, null);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, Long id, String name) {
		this(buttonType, ActionButtonType.NEXT, id, name, buttonType, null, null);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, Long id, String name, WordActionType wordActionType, long lessonId) {
		this(buttonType, ActionButtonType.NEXT, id, name, buttonType, wordActionType, lessonId);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, Long id, String name,
	                     MenuButtonType idType) {
		this(buttonType, actionType, id, name, idType, null, null);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, String name,
	                     WordActionType wordActionType) {
		this(buttonType, actionType, null, name, null, wordActionType, null);
	}
}
