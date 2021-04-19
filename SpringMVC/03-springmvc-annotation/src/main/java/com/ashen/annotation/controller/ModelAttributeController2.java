package com.ashen.annotation.controller;

import com.ashen.annotation.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 注解六：【ModelAttribute】
 * 【作用】
 *      它可以用于修饰方法和参数。
 *      出现在方法上，表示当前方法会在控制器的方法执行之前，先执行。它可以修饰没有返回值的方法，也可以修饰有具体返回值的方法。
 *      出现在参数上，获取指定的数据给参数赋值。
 * 【属性】
 *      value：用于获取数据的key。key 可以是POJO 的属性名称，也可以是map 结构的key。
 * 【应用场景】
 *      当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
 */
@Controller
@RequestMapping("/testModelAttribute")
public class ModelAttributeController2 {

    /**
     * 该方法会先执行，没有返回值，需要提供一个Map参数
     * @param username
     * @return
     */
    @ModelAttribute
    public void showModel(String username, Map<String, User> map) {
        // 模拟查询数据操作
        User user = new User();
        user.setUsername(username);
        user.setPassword("999999");
        user.setAge(23);
        System.out.println("showModel方法先执行了，存入map，key=aaa，value=" + user);
        map.put("aaa", user);
    }

    /**
     * 测试无返回值的ModelAttribute注解
     * 需要在参数上添加ModelAttribute，value属性写map的key，
     * 从而绑定showModel方法存入map的对应value值到参数上。
     * @param user 前台传来的缺少age
     * @return
     */
    @RequestMapping("/test2")
    public String testModelAttribute(@ModelAttribute("aaa") User user) {
        System.out.println("控制器返回值：" + user);
        return "success";
    }
}
