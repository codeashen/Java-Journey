package com.ashen.paya.service;

public interface PayService {
    String payment(String userId, String orderId, String accountId, double money);
}
