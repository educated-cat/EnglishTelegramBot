package com.educatedcat.englishtelegrambot.dictionary;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

@Slf4j
@Aspect
@Component
public class DictionaryServiceAspect {
	@Pointcut("execution(public * com.educatedcat.englishtelegrambot.dictionary.*.*.*(..))")
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
