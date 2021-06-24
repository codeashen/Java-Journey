package com.ashen.order.controller;

import com.ashen.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // // 超时降级
    // @HystrixCommand(
    //         commandKey = "createOrder",
    //         commandProperties = {
    //                 @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
    //                 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
    //         },
    //         fallbackMethod = "createOrderFallback4Timeout"
    // )
    // // 限流策略：线程池方式
    // @HystrixCommand(
    //         commandKey = "createOrder",
    //         commandProperties = {
    //                 @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
    //         },
    //         threadPoolKey = "createOrderThreadPool",
    //         threadPoolProperties = {
    //                 @HystrixProperty(name = "coreSize", value = "10"),
    //                 @HystrixProperty(name = "maxQueueSize", value = "20000"),
    //                 @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30")
    //         },
    //         fallbackMethod = "createOrderFallback4Thread"
    // )
    // // 限流策略：信号量方式
    // @HystrixCommand(
    //         commandKey = "createOrder",
    //         commandProperties = {
    //                 @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
    //                 @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "3")
    //         },
    //         fallbackMethod = "createOrderFallback4Semaphore"
    // )
    @RequestMapping("createOrder")
    public String createOrder(
            @RequestParam("cityId") String cityId,
            @RequestParam("platformId") String platformId,
            @RequestParam("userId") String userId,
            @RequestParam("supplierId") String supplierId,
            @RequestParam("goodsId") String goodsId) {
        return orderService.createOrder(cityId, platformId, userId, supplierId, goodsId) ? "下单成功!" : "下单失败!";
    }

    public String createOrderFallback4Timeout(
            @RequestParam("cityId") String cityId,
            @RequestParam("platformId") String platformId,
            @RequestParam("userId") String userId,
            @RequestParam("supplierId") String supplierId,
            @RequestParam("goodsId") String goodsId) {
        System.err.println("-------超时降级策略执行------------");
        return "hystrix timeout!";
    }

    public String createOrderFallback4Thread(
            @RequestParam("cityId") String cityId,
            @RequestParam("platformId") String platformId,
            @RequestParam("userId") String userId,
            @RequestParam("supplierId") String supplierId,
            @RequestParam("goodsId") String goodsId) {
        System.err.println("-------线程池限流降级策略执行------------");
        return "hystrix thread limit!";
    }

    public String createOrderFallback4Semaphore(
            @RequestParam("cityId") String cityId,
            @RequestParam("platformId") String platformId,
            @RequestParam("userId") String userId,
            @RequestParam("supplierId") String supplierId,
            @RequestParam("goodsId") String goodsId) {
        System.err.println("-------信号量限流降级策略执行------------");
        return "hystrix semaphore limit!";
    }

}
