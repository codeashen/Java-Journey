package com.ashen.authority.controller;

import com.github.pagehelper.PageInfo;
import com.ashen.authority.domain.Order;
import com.ashen.authority.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单Web层
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页查询所有订单
     * @param pageNum 页码
     * @param pageSize 每页容量
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int pageNum,
                                @RequestParam(name = "size", required = true, defaultValue = "4") int pageSize) {
        ModelAndView view = new ModelAndView("order-list");
        List<Order> orderList = orderService.findAll(pageNum, pageSize);

        /*
        PageInfo就是一个分页Bean，里面封装了结果集，和各种分页信息（中文源码）
        其中成员变量list，就是结果集，可以构造方法传入
        */
        PageInfo pageInfo = new PageInfo(orderList);
        view.addObject("pageInfo", pageInfo);
        return view;
    }

    /**
     * 根据订单号查询订单
     * @param orderId
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String orderId) {
        ModelAndView view = new ModelAndView("order-show");
        Order order = orderService.findById(orderId);
        view.addObject("order",order);
        return view;
    }
}
