package com.educatedcat.englishtelegrambot.botreceiver.word;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;

public record WordAction(Long id, WordActionType actionType, long lessonId) implements ButtonMarker {
}
