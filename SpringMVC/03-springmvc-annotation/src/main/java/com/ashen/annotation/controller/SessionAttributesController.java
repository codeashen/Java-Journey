package com.ashen.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * 注解七：【SessionAttributes】
 * 【作用】
 *      用于多次执行控制器方法间的参数共享。就是操作session中的attribute
 * 【属性】
 *      value：用于指定存入的属性名称
 *      type：用于指定存入的数据类型。
 *      最后存入Session的值有，value指定的属性 和 所有的type指定的类型的属性
 */
@Controller
@RequestMapping("/testSessionAttributes")
// 名叫username、password，类型为Integer的属性都会存入session域
@SessionAttributes(value = {"username", "password"}, types = {Integer.class})
public class SessionAttributesController {

    /**
     * 把数据存入SessionAttribute
     * @param model Model 是spring 提供的一个接口，该接口有一个实现类ExtendedModelMap,
     *              该类继承了ModelMap，而ModelMap 就是LinkedHashMap 子类
     * @return
     */
    @RequestMapping("/saveAttributes")
    public String saveAttributes(Model model) {
        // addAttribute方法底层会将属性存入request域对象中
        model.addAttribute("username", "小明");
        model.addAttribute("password", "123456");
        model.addAttribute("age", "23");
        // 跳转之前将数据保存到username、password 和age 中，因为注解@SessionAttribute 中有这几个参数
        return "welcome";
    }

    /**
     * 获取session域对象中的属性
     * @param modelMap LinkedHashMap子类，get方法可以获取对应key的属性value
     * @return
     */
    @RequestMapping("/getAttributes")
    public String getAttributes(ModelMap modelMap) {
        System.out.println(modelMap.get("username") + ";" + modelMap.get("password") +
                ";" + modelMap.get("age"));
        return "welcome";
    }

    /**
     * 清除session域对象中的属性
     * @param sessionStatus 该对象的setComplete方法可以清除session域对象中的属性
     * @return
     */
    @RequestMapping("removeAttributes")
    public String removeAttributes(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "welcome";
    }
}
