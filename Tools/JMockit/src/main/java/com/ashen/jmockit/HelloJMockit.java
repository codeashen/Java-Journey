package com.ashen.jmockit;

import java.util.Locale;

public class HelloJMockit {
    public String sayHello() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            return "你好, JMockit";
        } else {
            return "Hello, JMockit";
        } 
    }
}
