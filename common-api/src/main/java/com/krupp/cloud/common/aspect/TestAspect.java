package com.krupp.cloud.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TestAspect {

    Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Around(value = "@annotation(com.krupp.cloud.common.annotation.TestAnnotation)")
    public Object doAroundService(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        logger.info("调用方法：{}，参数：{}，执行耗时：{}纳秒，耗时：{}毫秒",
                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()), (end - begin), (end - begin) / 1000000);
        return obj;
    }

}