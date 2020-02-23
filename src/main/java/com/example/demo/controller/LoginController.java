package com.example.demo.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Dto;
import com.example.demo.entity.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.util.GithubProvider;

@Controller
public class LoginController {
	@Autowired
	private GithubProvider gitprovider;
	
	@Autowired
	private UserMapper um;
	
	@Value("${github.client_id}")
	private String  client_id;
	
	@Value("${github.client_secret}")
	private String client_secret;
	
	@Value("${github.redirect_uri}")
	private String redirect_uri;
	
	
	@RequestMapping("/callback")
	public String callBackFromGithub(@RequestParam(name="code")	String code,
									 @RequestParam(name="state")	String state,
									 HttpServletRequest request
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
		if(user!=null) {
			UserModel usermodel=new UserModel();
			usermodel.setToken(UUID.randomUUID().toString());
			usermodel.setName(user.getName());
			usermodel.setAccountId(user.getId());
			um.insertUser(usermodel);
			
		request.getSession().setAttribute("user", user);
		
		return "index";
		}
		
		return "index";
	}
	
}
