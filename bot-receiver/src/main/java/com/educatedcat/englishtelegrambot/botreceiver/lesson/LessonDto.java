package com.educatedcat.englishtelegrambot.botreceiver.lesson;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;

public record LessonDto(long id, String name) implements ButtonMarker {
}
