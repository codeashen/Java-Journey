package com.ashen.response.controller;

import com.ashen.response.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应和结果视图
 * Controller方法返回值有三种：String、void、ModelAndView
 *      String:  controller方法返回字符串可以指定逻辑视图名，通过视图解析器解析为物理视图地址。
 *      void:    空返回值的默认处理方式是，将请求路径作为视图名称，经过视图解析器去找，但是不会这么用。
 *               用法一：请求转发
 *               用法二：重定向
 *               用法三：通过response指定响应结果
 *      ModelAndView:  与返回String效果差不多，返回String底层就是返回ModelAndView
 */
@Controller
@RequestMapping("/response")
public class ResponseController {

    /**
     * 返回值类型一：String，经过视图解析器返回视图
     * @param model
     * @return 返回值类型一：String
     */
    @RequestMapping("/testString")
    public String testString(Model model) {
        User user = new User();
        user.setUsername("小明");
        return "success";
    }

    /**
     * 返回值类型二：void
     * 没有返回值，一般在参数获取Servlet的API，使用请求和响应对象来处理
     * @param request
     * @param response
     */
    @RequestMapping("/testVoid1")
    public void testVoid1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 方式一：请求转发。请求转发为服务器内部转发，从路径根路径写起，且不经过视图解析器
        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
    }

    @RequestMapping("/testVoid2")
    public void testVoid2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 方式二：重定向。重定向是客户端的第二次请求，所以要写完整的url
        response.sendRedirect(request.getContextPath() + "/redirect.jsp");
    }

    @RequestMapping("/testVoid3")
    public void testVoid3(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 方式三：response指定响应结果。如返回json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{'username':'Alice','age':23}");
    }

    /**
     * 返回值类型三：ModelAndView
     * 作用与String返回值相同，写法不同，无需再参数上声明Model了，返回值中存属性
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        // 创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        // 在ModelAndView对象中存入属性
        mv.addObject("username", "张三");
        // 设置跳转到那个页面
        mv.setViewName("success");
        return mv;
    }

    /* ================ 使用关键字完成请求转发和重定向 ================ */

    /**
     * 使用关键字完成请求转发和重定向，返回值用String，开头写 forward: 或 redirect:
     * @return
     */
    @RequestMapping("/testForward")
    public String testForward() {
        System.out.println("使用关键字完成请求转发");
        return "forward:/WEB-INF/pages/success.jsp";
    }

    /**
     * 关键字重定向，冒号后从项目根路径写起即可，底层会自动完善url
     * @return
     */
    @RequestMapping("/testRedirect")
    public String testRedirect() {
        System.out.println("使用关键字完成重定向");
        return "redirect:/redirect.jsp";
    }

    /* ================ Ajax请求发送/响应json ================ */

    /**
     * Ajax请求发送/响应json数据
     * 参数上用RequestBody注解自动将json字符串封装到对象，
     * ResponseBody注解也会将返回值对象转为json数据。（需要jackson相关依赖）
     * @param user
     * @return
     */
    @RequestMapping("/testAjax")
    @ResponseBody
    public User testAjax(@RequestBody User user) {
        System.out.println("Ajax请求参数：" + user);
        user.setUsername("王五");
        user.setAge(55);
        // throw new IllegalArgumentException();
        return user;
    }
}
