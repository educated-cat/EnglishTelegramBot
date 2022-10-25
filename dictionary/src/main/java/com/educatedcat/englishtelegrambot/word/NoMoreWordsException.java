package com.educatedcat.englishtelegrambot.word;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoMoreWordsException extends RuntimeException {
	public NoMoreWordsException() {
	}
	
	public NoMoreWordsException(String message) {
		super(message);
	}
	
	public NoMoreWordsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NoMoreWordsException(Throwable cause) {
		super(cause);
	}
	
	public NoMoreWordsException(String message, Throwable cause, boolean enableSuppression,
	                            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
