/**
 * Copyright (C), 2018-2019, 申通快递有限公司
 * FileName: CacheController
 * Author:   thinkpad
 * Date:     2019-06-04 0:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.web;
/**
 * Created by thinkpad on 2019-06-04.
 */

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author thinkpad
 * @create 2019-06-04
 * @since 1.0.0
 */
@RestController
public class CacheController {

    @RequestMapping("/hi")
    @Cacheable(value =
            "hello")
    public String hello(String name){
        System.out.println("没有走缓存！");
        return "hello "+name;
    }
}
