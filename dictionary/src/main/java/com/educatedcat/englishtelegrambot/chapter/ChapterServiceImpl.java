package com.educatedcat.englishtelegrambot.chapter;

import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
	private final ChapterRepository chapterRepository;
	
	@Override
	public List<Chapter> findAll() {
		return chapterRepository.findAll();
	}
}
