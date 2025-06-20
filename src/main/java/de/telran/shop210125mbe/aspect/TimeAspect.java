package de.telran.shop210125mbe.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class TimeAspect {
    private static final Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Pointcut("execution(public * de.telran.shop210125mbe.controller.UserController.*(..))")
    public void callAtUserControllerPublic() {} //Join-point

    @Before("callAtUserControllerPublic()")
    public void beforeCallAtMethod(JoinPoint jp) { //перед вызовом метода
        log.info("-- start -- "+jp.toString()+" time -> "+LocalDateTime.now());
        String args = Arrays.stream(jp.getArgs())
                     .map(a -> a.toString())
                     .collect(Collectors.joining(","));
        log.warn("before " + jp.toShortString() + ", args=[" + args + "]");
    }

    @After("callAtUserControllerPublic()")
    public void afterCallAt(JoinPoint jp) {
        log.info("-- end "+jp.toString()+" time -> "+ LocalDateTime.now());
    }
}
