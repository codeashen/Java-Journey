package com.ashen.jmockit;

import javax.annotation.Resource;

// 订单服务类 ,用于下订单
public class OrderService {
    // 邮件服务类，用于向某用户发邮件。
    MailService mailService;
    // 用户身份校验类，用于校验某个用户是不是合法用户
    @Resource
    UserCheckService userCheckService;

    // 构造函数
    public OrderService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 下订单
     *
     * @param buyerId 买家ID
     * @param itemId  商品id
     * @return 返回 下订单是否成功
     */
    public boolean submitOrder(long buyerId, long itemId) {
        // 先校验用户身份，不合法返回 false
        if (!userCheckService.check(buyerId)) {
            return false;
        }
        
        // 下单逻辑代码，省略......
        
        // 下单完成，给买家发邮件，返回是否发送成功
        return mailService.sendMail(buyerId, "下单成功");
    }
}