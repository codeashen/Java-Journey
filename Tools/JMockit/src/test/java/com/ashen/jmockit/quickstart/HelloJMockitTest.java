package com.ashen.jmockit.quickstart;

import com.ashen.jmockit.HelloJMockit;
import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class HelloJMockitTest {

    @Test
    public void sayHelloCH() {
        new Expectations(Locale.class) {{
            Locale.getDefault();
            result = Locale.CHINA;
        }};
        Assert.assertEquals("你好, JMockit", new HelloJMockit().sayHello());
    }

    @Test
    public void sayHelloUS() {
        new Expectations(Locale.class) {{
            Locale.getDefault();
            result = Locale.US;
        }};
        Assert.assertEquals("Hello, JMockit", new HelloJMockit().sayHello());
    }
}
