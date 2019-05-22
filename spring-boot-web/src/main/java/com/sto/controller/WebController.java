package com.sto.controller;

import com.sto.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    @RequestMapping("/web")
    public String web(ModelMap map){
        map.addAttribute("username", "ityouknow");
        return "web";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "no");
        return "web";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("username", "ityouknow");
        map.addAttribute("flag", "no");

        map.addAttribute("users", getUserList());
        return "web";
    }


    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("users", getUserList());
        map.addAttribute("flag", "yes");

        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11/");
        map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        return "web";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "web";
    }

    @RequestMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "web";
    }

    @RequestMapping("/object")
    public String object(HttpServletRequest request) {
        request.setAttribute("request","i am request");
        request.getSession().setAttribute("session","i am session");
        return "web";
    }

    @RequestMapping("/utility")
    public String utility(ModelMap map) {
        map.addAttribute("userName", "neo");
        map.addAttribute("users", getUserList());
        map.addAttribute("count", 12);
        map.addAttribute("date", new Date());
        return "web";
    }

    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("abc.@google.cn","大牛","123456");
        User user2=new User("abc1111.@google.cn","小牛","qwertyhgf");
        User user3=new User("abc22222.@google.cn","纯洁的微笑","666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
