package com.educatedcat.englishtelegrambot.bot.word;

import com.educatedcat.englishtelegrambot.bot.dictionary.*;

import java.util.*;

public record WordAction(UUID id, WordActionType actionType) implements ButtonMarker {
}
