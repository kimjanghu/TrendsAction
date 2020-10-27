package com.action.trends.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface KakaoAPIRepository {
	
	int isUserInfoExist(String email) throws Exception;
	
}
