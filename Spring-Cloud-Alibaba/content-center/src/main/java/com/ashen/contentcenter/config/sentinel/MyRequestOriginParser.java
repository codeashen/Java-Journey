package com.ashen.contentcenter.config.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 实现Sentinel的 RequestOriginParser 来源解析器接口，
 * 解析来自HTTP请求的请求来源（例如IP，用户，appName）
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {

    /**
     * 解析给定HTTP请求的来源
     * @param request http请求
     * @return 解析出的来源，返回什么就表示来源是什么
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 此处将origin参数解析为请求来源，也可以规定使用header等其他信息
        // String origin = request.getHeader("origin");
        String origin = request.getParameter("origin");
        if (StringUtils.isBlank(origin)) {
            // throw new IllegalArgumentException("error！");
            return "";
        }
        return origin;
    }
}
