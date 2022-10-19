package com.educatedcat.englishtelegrambot.bot.keyboard;

import com.educatedcat.englishtelegrambot.bot.button.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class KeyboardEntryMapper {
	public String serialize(KeyboardEntry entry) {
		return entry.buttonType().name() + ","
		       + entry.actionType().name() + ","
		       + (entry.id() == null ? "" : entry.id());
	}
	
	public KeyboardEntry deserialize(String serializedEntry, String name) {
		String[] elems = serializedEntry.split(",");
		final MenuButtonType buttonType = MenuButtonType.valueOf(elems[0]);
		final ActionButtonType actionType = ActionButtonType.valueOf(elems[1]);
		final UUID id = (elems.length == 2 || elems[2] == null) ? null : UUID.fromString(elems[2]);
		return new KeyboardEntry(buttonType, actionType, id, name);
	}
}
