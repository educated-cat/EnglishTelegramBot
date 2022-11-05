package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.keyboard.*;

public interface WordTextFactory {
	String buildText(KeyboardEntry entry);
}
