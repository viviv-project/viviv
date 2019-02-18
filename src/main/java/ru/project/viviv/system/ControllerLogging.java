package ru.project.viviv.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogging {

    private static final Logger log = LogManager.getLogger(ControllerLogging.class);

    @Around("execution(* ru.project.viviv.controller..*.*(..))")
    public Object logControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Log Around: <<< {} >>> : Before Method Execution", joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        log.info("Log Around: <<< {} >>> : After Method Execution", joinPoint.getSignature().getName());

        return result;
    }
}