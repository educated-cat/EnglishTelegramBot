package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;
import com.educatedcat.englishtelegrambot.botreceiver.dictionary.Language;

public record WordDto(long id, String name, String transcription, String translation, Language language)
		implements ButtonMarker {
}
