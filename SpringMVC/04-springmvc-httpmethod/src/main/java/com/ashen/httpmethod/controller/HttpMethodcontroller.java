package com.ashen.httpmethod.controller;

import com.ashen.httpmethod.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controller中方法在RequestMapping中设置了相同的请求路径，不同请求方式
 * put和delete请求经过视图解析器返回页面会报错，405，所以加上ResponseBody返回字符串
 * 详情见 https://blog.csdn.net/weixin_45165669/article/details/104617304
 */
@Controller
@RequestMapping("/method")
public class HttpMethodcontroller {

    /**
     * post 请求：保存
     */
    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPost(User user) {
        System.out.println("post请求执行：" + user);
        return "success";
    }

    /**
     * put 请求：更新
     */
    @ResponseBody
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable("id") Integer id, User user) {
        System.out.println("put请求执行：" + id + "," + user);
        return "success";
    }

    /**
     * delete 请求：删除
     */
    @ResponseBody
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable("id") Integer id) {
        System.out.println("delete请求执行：" + id);
        return "success";
    }
    /**
     * get 请求：查询
     */
    @RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
    public String testRestGet(@PathVariable("id")Integer id){
        System.out.println("get请求执行：" + id);
        return "success";
    }
}
