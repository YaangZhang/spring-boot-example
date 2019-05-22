package com.sto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class ThymeleafController {
	
    @RequestMapping("/web")
	public String hello(Locale locale, Model model) {
		System.out.println(model);
		model.addAttribute("greeting", "Hello   你好!");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);        
		String formattedDate = dateFormat.format(date);
		model.addAttribute("currentTime", formattedDate);
		return "hello";
	}

	@RequestMapping("/index")
	public String index(ModelMap model){
		System.out.println(model);
		model.addAttribute("message", "Hello   你好!");
    	return "index";
	}

	@RequestMapping("/fragment")
	public String fragment() {
		return "fragment";
	}

}