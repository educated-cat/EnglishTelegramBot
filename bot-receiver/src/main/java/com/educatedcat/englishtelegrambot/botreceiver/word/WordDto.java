package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;

import java.util.*;

public record WordDto(UUID id, String name, String transcription, String translation, Language language)
		implements ButtonMarker {
}
