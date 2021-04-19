package com.ashen.interceptor.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC拦截器类
 * 1. 实现HandlerInterceptor接口，接口中没有抽象方法，有三个默认方法，提供了方法体
 * 2. 拦截器对象要在SpringMVC配置文件中配置，见springmvc.xml
 *
 * 可以在preHandle 或postHandle 中使用请求、响应对象，转发或重定向等
 * 请求经过拦截器的执行顺序
 *      preHandle --放行--> controller方法 -> postHandle -> jsp代码 -> afterCompletion
 */
public class MyInterceptor1 implements HandlerInterceptor {

    /**
     * 预处理，在调用Controller方法前会调用此方法。
     * @param request
     * @param response
     * @param handler
     * @return true 放行，执行下一个拦截器preHandle方法，没有则执行controller中的方法；
     *         false不放行，执行上一个拦截器的postHandle方法，没有则直接响应浏览器。
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor1执行了...前1111");
        // 可以转发或重定向
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        return true;
    }

    /**
     * 后处理方法，controller方法执行后，success.jsp执行之前
     * 在调用Controller方法结束后、页面渲染之前调用此方法，比如可以在这里将渲染的视图名称更改为其他视图名称。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1执行了...后1111");
        // 可以转发或重定向
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
    }

    /**
     * 最终处理方法（响应完了），success.jsp页面执行后，该方法会执行
     * 页面渲染完毕后调用此方法，通常用来清除某些资源，类似Java语法的finally
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1执行了...最后1111");
        // 响应完了，就别转发或重定向了，浏览器收不到了
    }
}
