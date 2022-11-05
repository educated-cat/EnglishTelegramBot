package com.educatedcat.englishtelegrambot.botreceiver.keyboard;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;

import java.util.*;

public record KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, UUID id, String name,
                            MenuButtonType idType, WordActionType wordActionType) {
	public KeyboardEntry(MenuButtonType buttonType, String name) {
		this(buttonType, ActionButtonType.NEXT, null, name, buttonType, null);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, UUID id, String name) {
		this(buttonType, ActionButtonType.NEXT, id, name, buttonType, null);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, UUID id, String name, WordActionType wordActionType) {
		this(buttonType, ActionButtonType.NEXT, id, name, buttonType, wordActionType);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, UUID id, String name,
	                     MenuButtonType idType) {
		this(buttonType, actionType, id, name, idType, null);
	}
	
	public KeyboardEntry(MenuButtonType buttonType, ActionButtonType actionType, String name,
	                     WordActionType wordActionType) {
		this(buttonType, actionType, null, name, null, wordActionType);
	}
}
