package com.educatedcat.englishtelegrambot.botreceiver.chapter;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;

public record ChapterDto(long id, String name) implements ButtonMarker {
}
