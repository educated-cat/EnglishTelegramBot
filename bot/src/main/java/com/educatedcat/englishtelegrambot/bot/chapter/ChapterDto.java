package com.educatedcat.englishtelegrambot.bot.chapter;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;

import java.util.*;

public record ChapterDto(UUID id, String name) implements ButtonMarker {
}
