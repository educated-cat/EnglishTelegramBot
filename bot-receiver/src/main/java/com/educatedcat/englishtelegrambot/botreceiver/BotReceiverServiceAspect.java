package com.educatedcat.englishtelegrambot.botreceiver;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class BotReceiverServiceAspect {
	@Pointcut("execution(public * com.educatedcat.englishtelegrambot.botreceiver.*.*.*(..))")
	public void callAtAllPublicMethods() {
	}
	
	@Before("callAtAllPublicMethods()")
	public void logBefore(JoinPoint joinPoint) {
		if (joinPoint == null || joinPoint.getSignature() == null) {
			return;
		}
		log.debug("Executing {}", joinPoint.getSignature().toShortString());
	}
	
	@After("callAtAllPublicMethods()")
	public void logAfter(JoinPoint joinPoint) {
		if (joinPoint == null || joinPoint.getSignature() == null) {
			return;
		}
		log.debug("Completed {}", joinPoint.getSignature().toShortString());
	}
}
