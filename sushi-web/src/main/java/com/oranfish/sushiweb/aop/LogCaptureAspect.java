package com.oranfish.sushiweb.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

@Component
@Order(-1)
@Aspect
public class LogCaptureAspect {

    private static Logger logger = LoggerFactory.getLogger(LogCaptureAspect.class);

    @Around("@annotation(com.oranfish.sushiweb.aop.LogCapture) " +
            "|| execution(* *WithLog(..)) " +
            "|| execution(public * org.springframework.web.client.RestTemplate.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("方法路径{}.{}", methodSignature.getDeclaringType(), methodSignature.getMethod().getName());
        logger.info("方法路径签名{}", methodSignature.getMethod().toString());
        //获取方法参数类型数组

        Class[] paramTypeArray = methodSignature.getParameterTypes();

        logger.info("请求参数为{}",JSON.toJSONString(args));
        Object result = null;
        try {
            result = joinPoint.proceed();
            logger.info("响应结果为{}", JSON.toJSON(result));
        } catch (Throwable throwable) {
            logger.info("异常原因{}", getFullTrace(throwable));
            throw throwable;
        }
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

    private String getFullTrace(Throwable t) throws IOException {
        if(t == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            t.printStackTrace(new PrintStream(baos));
        }finally{
            baos.close();
        }
        return baos.toString();
    }

}
