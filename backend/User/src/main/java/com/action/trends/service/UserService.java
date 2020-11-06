package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.User;

public interface UserService {
	int checknickname(String nickname) throws Exception;
	
	User detail(int userId) throws Exception;
	
	User detailByEmail(String email) throws Exception;
	
	int update(User user) throws Exception;
	
	int delete(int userId) throws Exception;
	
	
	//////////////////////////////////////////////////
	
	List<Integer> userCategoryList(int userId) throws Exception;
	
	int[] updateUserCategory(List<Integer> previusUserCategoryList, List<Integer> newUserCategoryList, int userId) throws Exception;
	
	List<String> userCategoryListAsString(int userId) throws Exception;
}
