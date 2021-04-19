package com.ashen.contentcenter.config.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * UrlCleaner：Sentinel提供用于重置用于标识资源Url的接口
 */
@Component
public class MyUrlCleaner implements UrlCleaner {
    /**
     * 重置Url
     * @param originUrl 原始Url
     * @return 返回用于标识资源的Url
     */
    @Override
    public String clean(String originUrl) {
        // 让 /shares/1 和 /shares/2 都记作 /shares/{number}，视为相同资源
        String[] split = originUrl.split("/");
        return Arrays.stream(split)
                .map(e -> NumberUtils.isNumber(e) ? "{number}" : e)   //数字转换
                .reduce((a, b) -> a + "/" + b)                        //累加操作
                .orElse("");
    }
}
