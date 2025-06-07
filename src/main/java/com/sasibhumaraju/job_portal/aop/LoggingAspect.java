package com.sasibhumaraju.job_portal.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    private static final String pointCutpointCutClassPathForMethods = "execution(* com.sasibhumaraju.job_portal.controller.JobPostingController.*(..)) || " +
            "execution(* com.sasibhumaraju.job_portal.controller.AppUserController.*(..)) ||" +
            "execution(* com.sasibhumaraju.job_portal.controller.ApplyController.*(..)) ||" +
            "execution(* com.sasibhumaraju.job_portal.controller.WorkExperienceController.*(..))";


    private void logMessage(String className, String methodName, String args, String when, String level) {
        if (Objects.equals(level, "INFO")) {
            log.info("{}: {} method called for args {}", when, methodName, args);
        } else {
            log.error("{}: {} method called for args {}", when, methodName, args);
        }
    }

    @Before(pointCutpointCutClassPathForMethods )
    public void loggingBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String args =  Arrays.toString(joinPoint.getArgs()) ;
        logMessage(className,methodName,args,"Before","INFO");    }

    @After(pointCutpointCutClassPathForMethods )
    public void loggingAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String args =  Arrays.toString(joinPoint.getArgs()) ;
        logMessage(className,methodName,args,"After","INFO");    }

    @AfterThrowing(pointCutpointCutClassPathForMethods )
    public void loggingAfterThrowing(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String args =  Arrays.toString(joinPoint.getArgs()) ;
        logMessage(className,methodName,args,"After Throwing","ERROR");    }

    @AfterReturning(pointCutpointCutClassPathForMethods )
    public void loggingAfterReturing(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String args =  Arrays.toString(joinPoint.getArgs()) ;
        logMessage(className,methodName,args,"After Returning","INFO");    }
}
