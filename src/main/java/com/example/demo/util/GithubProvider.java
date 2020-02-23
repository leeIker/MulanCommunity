package com.example.demo.util;


import java.io.IOException;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Dto;
import com.example.demo.entity.GithubUser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GithubProvider {
	public String getAccessToken(Dto dto) {
		 MediaType mydiatype = MediaType.get("application/json; charset=utf-8");
		 OkHttpClient client = new OkHttpClient();
		 RequestBody body = RequestBody.create( mydiatype,JSON.toJSONString(dto) );
		 Request request = new Request.Builder()
				 								.url("https://github.com/login/oauth/access_token")
				 								.post(body)
				 								.build();
		
		 try{
			 Response response = client.newCall(request).execute();
			 String str=response.body().string();
			 String str_1=str.split("&")[0];
			 String ture_token=str_1.split("=")[1];
		  	 System.out.println(str);
		  	 return ture_token; 
		  }catch(Exception e){
	    	 e.printStackTrace();
	     }
		 return null;
		  
	}
	
	public GithubUser getUser(String access_token) {
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
												.url("https://api.github.com/user?access_token="+access_token)
												.build();

		try {
			Response response = client.newCall(request).execute();
			String str=response.body().string();
			GithubUser user=JSON.parseObject(str,GithubUser.class);
			return user;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
