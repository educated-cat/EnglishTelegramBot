package com.educatedcat.englishtelegrambot.bot.course;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;

import java.util.*;

public record CourseDto(UUID id, String name) implements ButtonMarker {
}
