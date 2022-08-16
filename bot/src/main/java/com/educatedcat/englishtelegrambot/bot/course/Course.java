package com.educatedcat.englishtelegrambot.bot.course;

import lombok.*;

@Getter
public enum Course {
	BEGINNERS("Beginners"),
	INTERMEDIATE("Intermediate");
	
	private final String name;
	
	Course(String name) {
		this.name = name;
	}
}
