package com.educatedcat.englishtelegrambot.bot.dictionary;

import lombok.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DictionaryClient {
	private final WebClient dictionaryWebClient;
	
	public List<CourseDto> findCourses() {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("courses").build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<CourseDto>>() {
				                          }))
		                          .block();
	}
	
	public List<ChapterDto> findChaptersInCourse(UUID courseId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", "by-course", courseId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	public List<ChapterDto> findChaptersInCourseById(UUID chapterId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", chapterId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	public List<ChapterDto> findChaptersInCourseByLessonId(UUID lessonId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", "by-lesson", lessonId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	public List<LessonDto> findLessonsInChapter(UUID chapterId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("lessons", "by-chapter", chapterId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<LessonDto>>() {
				                          }))
		                          .block();
	}
	
	public List<LessonDto> findLessonsInChapterById(UUID id) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("lessons", id.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<LessonDto>>() {
				                          }))
		                          .block();
	}
	
	public List<WordDto> findWordsInLesson(UUID lessonId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("words", "by-lesson", lessonId.toString())
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<WordDto>>() {
				                          }))
		                          .block();
	}
}
