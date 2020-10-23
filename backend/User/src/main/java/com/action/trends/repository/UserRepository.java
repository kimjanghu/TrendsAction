package com.action.trends.repository;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.User;


@Repository
public interface UserRepository {
	
	int checknickname(String nickname) throws Exception;
	
	User detail(int userId) throws Exception;
	
	int update(User user) throws Exception;
	
	int delete(int userId) throws Exception;
}
