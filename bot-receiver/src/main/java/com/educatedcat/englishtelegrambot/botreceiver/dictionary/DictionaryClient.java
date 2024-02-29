package com.educatedcat.englishtelegrambot.botreceiver.dictionary;

import com.educatedcat.englishtelegrambot.botreceiver.chapter.ChapterDto;
import com.educatedcat.englishtelegrambot.botreceiver.course.CourseDto;
import com.educatedcat.englishtelegrambot.botreceiver.lesson.LessonDto;
import com.educatedcat.englishtelegrambot.botreceiver.word.NoMoreWordsException;
import com.educatedcat.englishtelegrambot.botreceiver.word.WordDto;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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
	public List<ChapterDto> findChaptersInCourse(long courseId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", "by-course", String.valueOf(courseId))
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find chapters in course by chapter ID")
	public List<ChapterDto> findChaptersInCourseById(long chapterId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", String.valueOf(chapterId))
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find chapters in course by lesson ID")
	public List<ChapterDto> findChaptersInCourseByLessonId(long lessonId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("chapters", "by-lesson", String.valueOf(lessonId))
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<ChapterDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find lessons in chapter by chapter ID")
	public List<LessonDto> findLessonsInChapter(long chapterId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("lessons", "by-chapter", String.valueOf(chapterId))
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<LessonDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find lessons in chapter by ID")
	public List<LessonDto> findLessonsInChapterById(long id) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("lessons", String.valueOf(id))
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                          new ParameterizedTypeReference<List<LessonDto>>() {
				                          }))
		                          .block();
	}
	
	@NewSpan("Find first word in lesson")
	public WordDto findFirstWordInLesson(long lessonId) {
		return dictionaryWebClient.get()
		                          .uri(builder -> builder.pathSegment("words", "first", String.valueOf(lessonId))
		                                                 .build())
		                          .exchangeToMono(clientResponse -> clientResponse.bodyToMono(WordDto.class))
		                          .block();
	}
	
	@NewSpan("Find next word in lesson")
	public WordDto findNextWord(long previousWordId) {
		Mono<WordDto> wordDtoMono = dictionaryWebClient.get()
		                                               .uri(builder -> builder.pathSegment("words", "next",
		                                                                                   String.valueOf(previousWordId))
		                                                                      .build())
		                                               .exchangeToMono(clientResponse -> clientResponse.bodyToMono(
				                                               WordDto.class));
		if (Boolean.FALSE.equals(wordDtoMono.hasElement().block())) {
			throw new NoMoreWordsException();
		}
		return wordDtoMono.block();
	}
}
