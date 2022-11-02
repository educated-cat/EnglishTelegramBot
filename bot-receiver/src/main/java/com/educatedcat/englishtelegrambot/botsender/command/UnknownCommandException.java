package com.educatedcat.englishtelegrambot.botsender.command;

@SuppressWarnings("unused")
public class UnknownCommandException extends RuntimeException {
	public UnknownCommandException() {
		super();
	}
	
	public UnknownCommandException(String message) {
		super(message);
	}
	
	public UnknownCommandException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UnknownCommandException(Throwable cause) {
		super(cause);
	}
	
	protected UnknownCommandException(String message, Throwable cause, boolean enableSuppression,
	                                  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
