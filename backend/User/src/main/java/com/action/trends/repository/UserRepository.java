package com.action.trends.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.User;
import com.action.trends.dto.UserStringCategory;


@Repository
public interface UserRepository {
	
	int checknickname(String nickname) throws Exception;
	
	User detail(int userId) throws Exception;
	
	User detailByEmail(String detailByEmail) throws Exception;
	
	int update(UserStringCategory user) throws Exception;
	
	int delete(int userId) throws Exception;
	
	///////////////////////////////////////////////////////
	
	List<Integer> userCategoryList(int userId) throws Exception;
	
	int insertUserCategory(String categoryName, int userId) throws Exception;
	
	int deleteUserCategory(String categoryName, int userId) throws Exception;
	
	List<String> userCategoryListAsString(int userId) throws Exception;
}
