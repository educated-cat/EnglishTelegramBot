package com.educatedcat.englishtelegrambot.botsender.lesson;

import com.educatedcat.englishtelegrambot.botsender.dictionary.*;

import java.util.*;

public record LessonDto(UUID id, String name) implements ButtonMarker {
}
