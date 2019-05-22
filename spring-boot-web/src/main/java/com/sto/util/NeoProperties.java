package com.sto.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件
 * @Component 的定义为实例，方便在 Spring Boot 项目中引用；
 * 当需要从配置文件加载单个配置内容时，只需要使用 @Value 属性即可
 *
 *读取多个配置
 * @Component
 * @ConfigurationProperties(prefix="neo") 表示以 neo 开头的属性会自动赋值到对象的属性中
 * 比如，neo.title 会自动赋值到对象属性 title 中。
 *
 * 自定义配置文件
 * @Component
 * @ConfigurationProperties(prefix="other")
 * @PropertySource("classpath:other.properties") 多了一个注解来指明配置文件地址
 *
 */
@Component
public class NeoProperties {
	
	@Value("${com.neo.title}")
	private String title;
	@Value("${com.neo.description}")
	private String description;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
