package com.educatedcat.englishtelegrambot.botreceiver.course;

import com.educatedcat.englishtelegrambot.botreceiver.dictionary.*;

import java.util.*;

public record CourseDto(UUID id, String name) implements ButtonMarker {
}
