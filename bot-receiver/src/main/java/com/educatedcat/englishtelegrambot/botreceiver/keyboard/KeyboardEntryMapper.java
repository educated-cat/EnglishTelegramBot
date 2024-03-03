package com.educatedcat.englishtelegrambot.botreceiver.keyboard;

import com.educatedcat.englishtelegrambot.botreceiver.button.ActionButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.button.MenuButtonType;
import com.educatedcat.englishtelegrambot.botreceiver.word.WordActionType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class KeyboardEntryMapper {
	public String serialize(KeyboardEntry entry) {
		return String.join(";",
		                   entry.buttonType().name(),
		                   entry.actionType().name(),
		                   entry.id() == null ? " " : entry.id().toString(),
		                   entry.idType() == null ? " " : entry.idType().name(),
		                   entry.wordActionType() == null ? " " : entry.wordActionType().name(),
		                   entry.lessonId() == null ? " " : entry.lessonId().toString());
	}
	
	public KeyboardEntry deserialize(String serializedEntry, String name) {
		String[] elems = serializedEntry.split(";");
		final MenuButtonType buttonType = MenuButtonType.valueOf(elems[0]);
		final ActionButtonType actionType = ActionButtonType.valueOf(elems[1]);
		final Long id = !StringUtils.hasText(elems[2]) ? null : Long.valueOf(elems[2]);
		final MenuButtonType backButtonIdType = MenuButtonType.valueOf(elems[3]);
		final WordActionType wordAction = !StringUtils.hasText(elems[4]) ? null : WordActionType.valueOf(elems[4].toUpperCase());
		final Long lessonId = !StringUtils.hasText(elems[5]) ? null : Long.valueOf(elems[5]);
		return new KeyboardEntry(buttonType, actionType, id, name, backButtonIdType, wordAction, lessonId);
	}
}
