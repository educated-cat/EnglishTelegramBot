package com.educatedcat.englishtelegrambot.botreceiver.keyboard;

import com.educatedcat.englishtelegrambot.botreceiver.button.*;
import com.educatedcat.englishtelegrambot.botreceiver.word.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.util.*;

@Component
public class KeyboardEntryMapper {
	public String serialize(KeyboardEntry entry) {
		return String.join(";",
		                   entry.buttonType().name(),
		                   entry.actionType().name(),
		                   entry.id() == null ? " " : entry.id().toString(),
		                   entry.idType() == null ? " " : entry.idType().name(),
		                   entry.wordActionType() == null ? " " : entry.wordActionType().name());
	}
	
	public KeyboardEntry deserialize(String serializedEntry, String name) {
		String[] elems = serializedEntry.split(";");
		final MenuButtonType buttonType = MenuButtonType.valueOf(elems[0]);
		final ActionButtonType actionType = ActionButtonType.valueOf(elems[1]);
		final UUID id = !StringUtils.hasText(elems[2]) ? null : UUID.fromString(elems[2]);
		final MenuButtonType backButtonIdType = MenuButtonType.valueOf(elems[3]);
		final WordActionType wordAction = !StringUtils.hasText(elems[4]) ? null
		                                                                 : WordActionType.valueOf(
				                                                                 elems[4].toUpperCase());
		return new KeyboardEntry(buttonType, actionType, id, name, backButtonIdType, wordAction);
	}
}
