package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.keyboard.KeyboardEntry;

public interface WordTextFactory {
	String buildText(KeyboardEntry entry);
}
