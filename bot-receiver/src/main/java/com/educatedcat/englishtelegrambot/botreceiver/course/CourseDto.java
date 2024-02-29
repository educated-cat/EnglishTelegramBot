package com.educatedcat.englishtelegrambot.botreceiver.course;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.ButtonMarker;

public record CourseDto(Long id, String name) implements ButtonMarker {
}
