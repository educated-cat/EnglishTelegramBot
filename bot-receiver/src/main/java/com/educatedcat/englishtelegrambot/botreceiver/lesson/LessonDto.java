package com.educatedcat.englishtelegrambot.botreceiver.lesson;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;

import java.util.*;

public record LessonDto(UUID id, String name) implements ButtonMarker {
}
