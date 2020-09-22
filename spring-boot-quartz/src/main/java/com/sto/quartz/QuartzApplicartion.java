package com.sto.quartz; /**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: QuartzApplicartion
 * Author:   admin
 * Date:     2020/9/22 16:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2020/9/22
 * @since 1.0.0
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.sto.quartz.mapper")
public class QuartzApplicartion {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(QuartzApplicartion.class, args);
    }
}
