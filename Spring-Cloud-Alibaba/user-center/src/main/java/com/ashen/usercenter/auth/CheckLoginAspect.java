package com.ashen.usercenter.auth;

import com.ashen.usercenter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class CheckLoginAspect {
    
    @Resource
    private JwtOperator jwtOperator;
    
    @Around("@annotation(com.ashen.usercenter.auth.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) throws Throwable {
        // 1.从header中获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("X-Token");

        // 2.校验token有效性和时效性
        if (StringUtils.isBlank(token) || !jwtOperator.validateToken(token)) {
            log.warn("token check failed, token={}", token);
            throw new RuntimeException("无效Token");
        }
        
        // 3.如果校验成功，就将解析token获取用户信息，存到request的attribute中
        Claims claims = jwtOperator.getClaimsFromToken(token);
        request.setAttribute("id", claims.get("id"));
        request.setAttribute("wxNickname", claims.get("wxNickname"));
        request.setAttribute("role", claims.get("role"));

        log.warn("token check passed, token={}", token);
        return point.proceed();
    }
    
}
