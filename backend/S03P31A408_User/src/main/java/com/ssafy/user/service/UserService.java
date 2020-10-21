package com.ssafy.user.service;

import com.ssafy.user.dto.User;

public interface UserService {
	int checknickname(String nickname) throws Exception;
	
	User detail(int userId) throws Exception;
	
	int update(User user) throws Exception;
	
	int delete(int userId) throws Exception;
}
