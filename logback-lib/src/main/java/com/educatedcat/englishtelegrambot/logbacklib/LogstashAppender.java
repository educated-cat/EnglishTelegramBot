package com.educatedcat.englishtelegrambot.logbacklib;

import net.logstash.logback.appender.LogstashTcpSocketAppender;

public class LogstashAppender extends LogstashTcpSocketAppender {
	@Override
	public synchronized void start() {
		String logstashUri = LoggingCloudConfigurator.logstashUri();
		this.addDestination(logstashUri);
		super.start();
	}
}
