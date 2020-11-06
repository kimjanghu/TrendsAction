package com.action.trends.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.User;


@Repository
public interface UserRepository {
	
	int checknickname(String nickname) throws Exception;
	
	User detail(int userId) throws Exception;
	
	User detailByEmail(String detailByEmail) throws Exception;
	
	int update(User user) throws Exception;
	
	int delete(int userId) throws Exception;
	
	///////////////////////////////////////////////////////
	
	List<Integer> userCategoryList(int userId) throws Exception;
	
	int insertUserCategory(int categoryId, int userId) throws Exception;
	
	int deleteUserCategory(int categoryId, int userId) throws Exception;
	
	List<String> userCategoryListAsString(int userId) throws Exception;
}
