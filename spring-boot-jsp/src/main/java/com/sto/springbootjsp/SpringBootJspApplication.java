package com.sto.springbootjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJspApplication.class, args);
    }

    /**
     * 在单独的 Tomcat 中运行
     * （1）在 pom.xml 里设置打包格式为 war。
     * （2）排除内嵌的 Tomcat 依赖  打包时排除掉内嵌的 Tomcat 依赖，避免 jar 包冲突。
     * （3）Servlet 的支持
     * Spring Boot 项目必须实现 SpringBootServletInitializer 接口的 configure() 方法才能让外部容器运行 Spring Boot 项目，启动类同目录下创建 ServletInitializer 类：
     */

    class ServletInitializer extends SpringBootServletInitializer{
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
            return builder.sources(SpringBootJspApplication.class);
        }
    }
}
