package com.educatedcat.englishtelegrambot.logbacklib;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class LoggingCloudConfigurator {
	@NonNull
	public static String logstashUri() {
		return Objects.toString(System.getenv("LOGSTASH_URI"), "127.0.0.1:5044");
	}
}
