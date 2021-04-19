package com.ashen.authority.controller;

import com.ashen.authority.domain.SysLog;
import com.ashen.authority.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 系统AOP日志web层
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有系统日志
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("syslog-list");
        List<SysLog> sysLogList = sysLogService.findAll();
        view.addObject("sysLogs", sysLogList);
        return view;
    }
}
