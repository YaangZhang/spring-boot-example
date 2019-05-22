/**
 * Copyright (C), 2015-2019, 申雪供应链有限公司
 * FileName: HelloController
 * Author:   Administrator
 * Date:     2019-04-26 16:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.controller;/**
 * Created by Administrator on 2019-04-26.
 */

import com.sto.domain.User;
import com.sto.util.NeoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019-04-26
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private NeoProperties neoProperties;

    @RequestMapping("/hi")
    public String hello(Locale locale, Model model) {
        System.out.println(locale);
        System.out.println(model);
        return "hello world……";
    }

    /**
     * http://localhost:8072/set?userName=rose&passWord=123
     * @param user
     * @return
     */
    @RequestMapping("/set")
    public User setUser(User user) {
        System.out.println("user :"+user);
        return user;
    }

    /**
     * http://localhost:8072/saveUser?userName=rose&passWord=123
     *
     * user:User{id=null, userName='rose', passWord='123', email='null', nickName='null', regTime='null'}
     * Length-密码长度不能小于6位
     *
     * @Valid 参数前面添加 @Valid 注解，代表此对象使用了参数校验；
     * BindingResult 参数校验的结果会存储在此对象中，可以根据属性判断是否校验通过，校验不通过可以将错误信息打印出来。
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping("/saveUser")
    public User saveUser(@Valid User user, BindingResult result) {
        System.out.println("user:"+user);
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.getCode()+ "-" + error.getDefaultMessage());
            }
        }
        return user;
    }

    @RequestMapping("/getUser")
    public User getUser() {
        User user=new User();
        user.setUserName("小明");
        user.setPassWord("xxxx");
        return user;
    }

    @RequestMapping("/config")
    public String getConfig(){
        return neoProperties.getTitle()+" : "+neoProperties.getDescription();
    }

}
