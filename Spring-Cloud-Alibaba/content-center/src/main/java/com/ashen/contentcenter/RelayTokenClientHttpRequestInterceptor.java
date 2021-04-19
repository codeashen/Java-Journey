package com.ashen.contentcenter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 拦截器，可向RestTemplate中注册拦截器
 */
public class RelayTokenClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 1.取出原始请求头中的token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest httpServletRequest = attributes.getRequest();
        String token = httpServletRequest.getHeader("X-Token");
        // 2.设置header
        HttpHeaders headers = request.getHeaders();
        headers.add("X-Token", token);
        // 继续执行（后面还有拦截器的话继续，责任链模式）
        return execution.execute(request, body);
    }
}
