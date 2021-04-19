package com.ashen.annotation.controller;

import com.ashen.annotation.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class ModelAttributeController1 {

    /**
     * 该方法会先执行，有返回值
     * @param username
     * @return
     */
    @ModelAttribute
    public User showModel(String username) {
        // 模拟查询数据操作
        User user = new User();
        user.setUsername(username);
        user.setPassword("999999");
        user.setAge(23);
        System.out.println("showModel方法先执行了，返回值：" + user);
        return user;
    }

    /**
     * 测试带返回值的ModelAttribute注解
     * showModel方法先执行，返回了一个user对象，返回值会填充前台没传的age到参数上，
     * 但是已经有的值不会覆盖，如password
     * @param user 前台传来的缺少age
     * @return
     */
    @RequestMapping("/test1")
    public String testModelAttribute(User user) {
        System.out.println("控制器返回值：" + user);
        return "success";
    }
}
