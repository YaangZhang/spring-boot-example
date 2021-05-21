package com.sto.websocket.controller;

import com.sto.websocket.redis.RedisKey;
import com.sto.websocket.redis.RedisPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    @Autowired
    private RedisPubService redisPubService;

    @RequestMapping("/")
    public String chat(){
        return "chat";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/webSocket")
    public String webSocket(){
        return "webSocket";
    }

    @ResponseBody
    @RequestMapping("/send/{msg}")
    public String webSocket(@PathVariable String msg){
        redisPubService.sendChannelMess(RedisKey.REDIS_MQ_CHAT, msg);
        return "ok";
    }


}
