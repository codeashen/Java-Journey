package com.ashen.order.constants;

public enum OrderStatus {
    ORDER_CREATED("1"),
    ORDER_PAYED("2"),
    ORDER_FAIL("3");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }
}
