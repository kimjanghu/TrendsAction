package com.ssafy.user.repository;

import org.springframework.stereotype.Repository;

import com.ssafy.user.dto.User;


@Repository
public interface UserRepository {
	
	int checknickname(String nickname) throws Exception;
	
	User detail(int userId) throws Exception;
	
	int update(User user) throws Exception;
	
	int delete(int userId) throws Exception;
}
