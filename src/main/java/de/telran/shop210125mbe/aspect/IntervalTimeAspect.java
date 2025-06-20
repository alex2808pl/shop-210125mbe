package de.telran.shop210125mbe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class IntervalTimeAspect {

    @Around("@annotation(LogTimeAnnotation)")
    public Object aroundCallAt(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed(); //запуск перехваченного метода вручную
        log.info("!!! " + pjp.getSignature().getName() + " отработал за "+(System.currentTimeMillis()-startTime));
        return result;
    }
}
