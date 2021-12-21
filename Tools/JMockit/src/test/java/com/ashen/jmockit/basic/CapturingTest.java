package com.ashen.jmockit.basic;

import com.ashen.jmockit.IPrivilege;
import mockit.Capturing;
import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// @Capturing注解用途
public class CapturingTest {
    // 测试用户ID（黑名单用户）
    long blackUserId = -1L;

    // 权限检验类，可能是人工写的
    IPrivilege privilegeManager1 = new IPrivilege() {
        @Override
        public boolean isAllow(long userId) {
            return userId != blackUserId;
        }
    };

    // 权限检验类，可能是JDK动态代理生成。我们通常AOP来做权限校验。
    IPrivilege privilegeManager2 = (IPrivilege) Proxy.newProxyInstance(
            IPrivilege.class.getClassLoader(),
            new Class[]{IPrivilege.class},
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) {
                    return (long) args[0] != blackUserId;
                }
            });

    // 有Capturing情形
    @Test
    public void testCapturing(@Capturing IPrivilege privilegeManager) {
        // 加上了JMockit的API @Capturing,
        // JMockit会帮我们实例化这个对象，它除了具有@Mocked的特点，还能影响它的子类/实现类
        new Expectations() {{
            // 对IPrivilege的所有实现类录制，假设测试用户有权限
            privilegeManager.isAllow(blackUserId);
            result = true;
        }};
        // 不管权限校验的实现类是哪个，这个测试用户都有权限（测试发现实现类不能用 Lambda 表达式）
        Assert.assertTrue(privilegeManager1.isAllow(blackUserId));
        Assert.assertTrue(privilegeManager2.isAllow(blackUserId));
    }

    // 没有Capturing情形
    @Test
    public void testWithoutCapturing() {
        // 不管权限校验的实现类是哪个，这个测试用户没有权限
        Assert.assertFalse(privilegeManager1.isAllow(blackUserId));
        Assert.assertFalse(privilegeManager2.isAllow(blackUserId));
    }
}