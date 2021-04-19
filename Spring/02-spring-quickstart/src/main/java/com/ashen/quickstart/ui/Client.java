package com.ashen.quickstart.ui;

import com.ashen.quickstart.dao.IUserDao;
import com.ashen.quickstart.service.IUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。
     *      FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件(必须有访问权限）。
     *      AnnotationConfigApplicationContext：它是用于读取注解创建容器的。
     *
     * 核心容器的两个接口引发出的问题：
     *  ApplicationContext:     单例对象适用              采用此接口
     *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。（默认情况，配置为多例时延迟加载，详见下模块）
     *      也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
     *      ps：准确的说，此接口创建Spring容器时，创建所有单例的Bean对象
     *
     *  BeanFactory:            多例对象使用
     *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。
     *      也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     */
    public static void main(String[] args) {
        // ================== ApplicationContext =====================
        // 1.获取核心容器对象（【立即加载】执行下行代码时，所有的单例对象就已经创建了）
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.根据id获取Bean对象
        IUserService service = (IUserService) context.getBean("userService");
        IUserDao dao = context.getBean("userDao", IUserDao.class);

        System.out.println(service);
        System.out.println(dao);
    }

    /**
     * BeanFactory获取Bean对象
     */
    public static void beanFactoryTest() {
        // ===================== BeanFactory =========================
        // 1.创建BeanFactory
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        // 2.获取Bean对象（【延迟加载】此时才创建对象）
        IUserService service1 = (IUserService) factory.getBean("userService");
        IUserDao dao1 = factory.getBean("userDao", IUserDao.class);

        System.out.println(service1);
        System.out.println(dao1);
    }
}
