package com.educatedcat.englishtelegrambot.botreceiver.word;

@SuppressWarnings("unused")
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
