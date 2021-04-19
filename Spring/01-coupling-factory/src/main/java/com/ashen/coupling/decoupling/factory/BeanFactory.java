package com.ashen.coupling.decoupling.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 *      javabean >  实体类
 *
 *   它就是创建我们的service和dao对象的。
 *
 *   第一个：需要一个配置文件来配置我们的service和dao
 *           配置的内容：唯一标识=全限定类名（key=value)
 *   第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *   我的配置文件可以是xml也可以是properties
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;
    // 定义一个Map，用于存放我们要创建的对象。我们把它称之为容器
    private static Map<String, Object> beans;

    //使用静态代码块为Properties对象赋值
    static {
        try {
            // 实例化容器，用于存放Bean对象
            beans = new HashMap<>();
            // 创建Properties对象，加载配置文件
            props = new Properties();
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            // 获取配置文件所有的key枚举
            Enumeration keys = props.keys();
            // 遍历枚举
            while (keys.hasMoreElements()) {
                // 获取每个key
                String key = keys.nextElement().toString();
                // 根据key获取value，即全类名
                String beanPath = props.getProperty(key);
                // 反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                // 存到bean容器中
                beans.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据bean名称获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     * 这一版，没有使用bean容器，每次都创建新的bean对象，浪费
     * @param beanName
     * @return
    */
    public static Object getBean2(String beanName){
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            // System.out.println(beanPath);
            bean = Class.forName(beanPath).newInstance();//每次都会调用默认构造函数创建对象
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }
}
