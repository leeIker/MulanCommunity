package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.UserModel;

@Mapper
public interface UserMapper {
	@Insert("insert into mulan_user (user_name,user_accout_id,user_token)values (#{name},#{accountId},#{token})")
	void insertUser(UserModel user);
		
}
