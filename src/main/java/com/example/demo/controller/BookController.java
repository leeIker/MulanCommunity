package com.example.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
	@RequestMapping("querybook")
	@ResponseBody
	public String queryBook() {
		
		return "bookname";
	}
	@RequestMapping("bookpage")
	public String goBookPage(Map<String,String> map) {
		map.put("bookname", "挪威的森林");
		
	return "book";	
	}
}
