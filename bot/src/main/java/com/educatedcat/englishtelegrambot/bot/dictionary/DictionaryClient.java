package com.educatedcat.englishtelegrambot.bot.dictionary;

import com.educatedcat.englishtelegrambot.bot.chapter.*;
import com.educatedcat.englishtelegrambot.bot.course.*;
import com.educatedcat.englishtelegrambot.bot.lesson.*;
import com.educatedcat.englishtelegrambot.bot.word.*;
import lombok.*;
import org.springframework.cloud.sleuth.annotation.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DictionaryClient {
	private final WebClient dictionaryWebClient;
	
	@NewSpan("Find courses")
	public List<CourseDto> findCourses() {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("courses").build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<CourseDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find chapters in course by ID")
	public List<ChapterDto> findChaptersInCourse(UUID courseId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", "by-course", courseId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find chapters in course by chapter ID")
	public List<ChapterDto> findChaptersInCourseById(UUID chapterId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", chapterId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find chapters in course by lesson ID")
	public List<ChapterDto> findChaptersInCourseByLessonId(UUID lessonId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", "by-lesson", lessonId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find lessons in chapter by chapter ID")
	public List<LessonDto> findLessonsInChapter(UUID chapterId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("lessons", "by-chapter", chapterId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<LessonDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find lessons in chapter by ID")
	public List<LessonDto> findLessonsInChapterById(UUID id) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("lessons", id.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<LessonDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find first word in lesson")
	public WordDto findFirstWordInLesson(UUID lessonId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("words", "first", lessonId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(WordDto.class))
		                          .block();
	}
	
	@NewSpan("Find next word in lesson")
	public WordDto findNextWord(UUID previousWordId) {
		Mono<WordDto> wordDtoMono = dictionaryWebClient.get()
		                                               .uri(builder -> builder.pathSegment("words", "next",
		                                                                                   previousWordId.toString())
		                                                                      .build())
		                                               .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                                               WordDto.class));
		if (Boolean.FALSE.equals(wordDtoMono.hasElement().block())) {
			throw new NoMoreWordsException();
		}
		return wordDtoMono.block();
	}
}
