package com.ashen.account.factory;

import com.ashen.account.service.IAccountService;
import com.ashen.account.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service代理对象的工厂
 */
public class BeanFactory {

    // 被代理对象
    private IAccountService accountService;
    // 事务工具类
    private TransactionManager txManager;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 获取accountService的代理对象
     * @return
     */
    public IAccountService getAccountService() {
         IAccountService proxyAccountService = (IAccountService) Proxy.newProxyInstance(
                 accountService.getClass().getClassLoader(),
                 accountService.getClass().getInterfaces(),
                 new InvocationHandler() {
                     // 添加事务支持
                     @Override
                     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                         Object result = null; // 方法返回值
                         try {
                             // 1.开启事务
                             txManager.beginTx();
                             // 2.执行操作，得到结果
                             result = method.invoke(accountService, args);
                             // 3.提交事务
                             txManager.commit();
                             // 4.返回结果
                             return result;
                         } catch (Exception e) {
                             // 5.回滚事务
                             txManager.rollback();
                         } finally {
                             // 6.释放资源
                             txManager.release();
                         }
                         return result;
                     }
                 });
         return proxyAccountService;
    }
}
