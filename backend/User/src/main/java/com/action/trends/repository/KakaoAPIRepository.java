package com.action.trends.repository;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.User;

@Repository
public interface KakaoAPIRepository {
	
	int isUserInfoExist(String email) throws Exception;
	
    User userDetail(String email) throws Exception;
}
