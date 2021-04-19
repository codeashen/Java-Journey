package com.ashen.contentcenter.config.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BlockExceptionHandler接口用于处理限限流后处理
 * 来版本接口名叫UrlBlockHandler
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler  {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        // 根据不同的异常设置不同返回值
        BlockErrorMsg errorMsg = null;
        if (e instanceof FlowException) {
            errorMsg = new BlockErrorMsg(100, "限流了");
        } else if (e instanceof DegradeException) {
            errorMsg = new BlockErrorMsg(101, "降级了");
        } else if (e instanceof ParamFlowException) {
            errorMsg = new BlockErrorMsg(102, "热点参数限流");
        } else if (e instanceof AuthorityException) {
            errorMsg = new BlockErrorMsg(103, "授权规则不通过");
        } else if (e instanceof SystemBlockException) {
            errorMsg = new BlockErrorMsg(104, "系统规则限流");
        }
        
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");

        // 写出json响应
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorMsg));
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class BlockErrorMsg {
    private Integer status;
    private String msg;
}