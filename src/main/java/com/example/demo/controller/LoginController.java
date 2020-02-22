package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Dto;
import com.example.demo.entity.GithubUser;
import com.example.demo.util.GithubProvider;

@Controller
public class LoginController {
	@Autowired
	private GithubProvider gitprovider;
	
	@Value("${github.client_id}")
	private String  client_id;
	
	@Value("${github.client_secret}")
	private String client_secret;
	
	@Value("${github.redirect_uri}")
	private String redirect_uri;
	
	
	@RequestMapping("/callback")
	public String callBackFromGithub(@RequestParam(name="code")	String code,
									 @RequestParam(name="state")	String state
									) {
		System.out.println(code);
		Dto dto =new Dto();
		dto.setCode(code);
		dto.setState(state);
		dto.setRedirect_uri(redirect_uri);
		dto.setClient_id(client_id);
		dto.setClient_secret(client_secret);
		String access_token=gitprovider.getAccessToken(dto);
		System.out.println(access_token);
		GithubUser user=gitprovider.getUser(access_token);
		System.out.print(user.getName());
		return "index";
	}
	
}
