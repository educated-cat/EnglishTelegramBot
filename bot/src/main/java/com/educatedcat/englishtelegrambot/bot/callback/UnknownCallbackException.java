package com.educatedcat.englishtelegrambot.bot.callback;

@SuppressWarnings("unused")
public class UnknownCallbackException extends RuntimeException {
	public UnknownCallbackException() {
		super();
	}
	
	public UnknownCallbackException(String message) {
		super(message);
	}
	
	public UnknownCallbackException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UnknownCallbackException(Throwable cause) {
		super(cause);
	}
	
	protected UnknownCallbackException(String message, Throwable cause, boolean enableSuppression,
	                                   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
