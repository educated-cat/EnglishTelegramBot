package com.educatedcat.englishtelegrambot.botsender.course;

import com.educatedcat.englishtelegrambot.botsender.dictionary.*;

import java.util.*;

public record CourseDto(UUID id, String name) implements ButtonMarker {
}
