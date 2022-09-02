package com.educatedcat.englishtelegrambot.bot.command;

@SuppressWarnings("unused")
public class NotCommandException extends RuntimeException {
	public NotCommandException() {
		super();
	}
	
	public NotCommandException(String message) {
		super(message);
	}
	
	public NotCommandException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotCommandException(Throwable cause) {
		super(cause);
	}
	
	protected NotCommandException(String message, Throwable cause, boolean enableSuppression,
	                              boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
