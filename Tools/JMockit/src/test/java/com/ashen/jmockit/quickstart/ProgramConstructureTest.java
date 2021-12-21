package com.ashen.jmockit.quickstart;

import com.ashen.jmockit.HelloJMockit;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Assert;
import org.junit.Test;

// JMockit的程序结构
public class ProgramConstructureTest {
    // 这是一个测试属性
    @Mocked
    HelloJMockit helloJMockit;

    @Test
    public void test1() {
        // 录制（Record）
        new Expectations() {{
            helloJMockit.sayHello();
            result = "hello, Ashen";
        }};
        // 重放（Replay）
        String msg = helloJMockit.sayHello();
        Assert.assertEquals(msg, "hello, Ashen");
        // 验证（Verification）
        new Verifications() {{
            helloJMockit.sayHello();
            // 验证 helloJMockit.sayHello() 这个方法调用了 1 次
            times = 1;
        }};
    }

    @Test
    public void test2(@Mocked HelloJMockit helloJMockit /*这是一个参数测试*/) {
        // 录制（Record）
        new Expectations() {{
            helloJMockit.sayHello();
            result = "hello, Ashen";
        }};
        // 重放（Replay）
        String msg = helloJMockit.sayHello();
        Assert.assertEquals(msg, "hello, Ashen");
        // 验证（Verification）
        new Verifications() {{
            helloJMockit.sayHello();
            // 验证 helloJMockit.sayHello() 这个方法调用了 1 次
            times = 1;
        }};
    }
}
