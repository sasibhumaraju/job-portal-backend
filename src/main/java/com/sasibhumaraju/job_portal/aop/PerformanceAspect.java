package com.sasibhumaraju.job_portal.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
//    public static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAspect.class);
    private static final String pointCutpointCutClassPathForMethods = "execution(* com.sasibhumaraju.job_portal.controller.JobPostingController.*(..)) || " +
            "execution(* com.sasibhumaraju.job_portal.user.AppUserController.*(..)) ||" +
            "execution(* com.sasibhumaraju.job_portal.controller.ApplyController.*(..)) ||" +
            "execution(* com.sasibhumaraju.job_portal.controller.WorkExperienceController.*(..))";

    @Around(pointCutpointCutClassPathForMethods)
    public Object performanceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String args =  Arrays.toString(proceedingJoinPoint.getArgs()) ;
        log.info("Performance: {} method called for {} for args, time taken to execute: {} ms",methodName,args,(end-start));
        return obj;
    }
}
