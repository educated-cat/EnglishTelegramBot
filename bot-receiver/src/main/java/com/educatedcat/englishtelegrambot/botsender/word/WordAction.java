package com.educatedcat.englishtelegrambot.botsender.word;

import com.educatedcat.englishtelegrambot.botsender.dictionary.*;

import java.util.*;

public record WordAction(UUID id, WordActionType actionType) implements ButtonMarker {
}
