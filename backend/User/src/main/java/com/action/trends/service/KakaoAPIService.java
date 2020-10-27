package com.action.trends.service;

import java.util.HashMap;

public interface KakaoAPIService {
	String getAccessToken(String code) throws Exception;
	
	HashMap<String, Object> getUserInfo (String access_Token) throws Exception;
	
	int isUserInfoExist(String email) throws Exception;
}
