package com.educatedcat.englishtelegrambot.botreceiver.chapter;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;

import java.util.*;

public record ChapterDto(UUID id, String name) implements ButtonMarker {
}
