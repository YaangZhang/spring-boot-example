/**
 * Copyright (C), 2015-2019, 申雪供应链有限公司
 * FileName: WebConfiguration
 * Author:   Administrator
 * Date:     2019-04-26 16:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto;/**
 * Created by Administrator on 2019-04-26.
 */

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈自定义 Filter〉
 *自定义 Filter 有两种实现方式，第一种是使用 @WebFilter，第二种是使用 FilterRegistrationBean，经过实践之后发现使用 @WebFilter 自定义的过滤器优先级顺序不能生效，因此推荐使用第二个方案。
 *
 * 自定义 Filter 两个步骤：
 *
 * 实现 Filter 接口，实现其中的 doFilter() 方法；
 * 添加 @Configuration 注解，将自定义 Filter 加入过滤链。
 * @author Administrator
 * @create 2019-04-26
 * @since 1.0.0
 */
@Configuration
public class WebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }


    /**
     * 2、将自定义 Filter 加入过滤链：
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("paramName", "paramValue");
        registrationBean.setName("myFilter1");
        registrationBean.setOrder(6);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean test2FilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter2());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter2");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 1、新建 MyFilter 类，重写 doFilter() 方法：
     */
    public class MyFilter implements Filter{
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("MyFilter1 : init^^^^^^^^^^");
        }

        @Override
        public void destroy() {
            System.out.println("MyFilter1 : destroy^^^^^^^^^^");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest myRequest = (HttpServletRequest) request;
            System.out.println("this is MyFilter1 ,url :"+myRequest.getRequestURI());
            chain.doFilter(request, response);
        }
    }

    /**
     * 当有多个过滤器时可以通过设置 Order 属性来决定它们的执行顺序，Order 值越小优先级越高。
     */
    public class MyFilter2 implements Filter{
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("MyFilter2 : init^^^^^^^^^^");
        }

        @Override
        public void destroy() {
            System.out.println("MyFilter2 : destroy^^^^^^^^^^");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest myRequest = (HttpServletRequest) request;
            System.out.println("this is MyFilter2 ,url :"+myRequest.getRequestURI());
            chain.doFilter(request, response);
        }
    }
}
