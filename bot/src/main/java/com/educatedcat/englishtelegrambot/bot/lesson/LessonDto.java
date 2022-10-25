package com.educatedcat.englishtelegrambot.bot.lesson;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;

import java.util.*;

public record LessonDto(UUID id, String name) implements ButtonMarker {
}
