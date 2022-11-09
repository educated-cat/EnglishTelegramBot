package com.educatedcat.englishtelegrambot.userstatistics.word;

import reactor.core.publisher.*;

public interface WordProductivityService {
	Mono<WordProductivityDto> getByUserId(long userId);
}
