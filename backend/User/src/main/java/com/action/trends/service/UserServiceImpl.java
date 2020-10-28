package com.action.trends.service;



import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.User;
import com.action.trends.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repo;
	

	
	@Override
	public int checknickname(String nickname) throws Exception{
	
		int result = repo.checknickname(nickname);

		return result;

	}
	
	@Override
	public User detail(int userId) throws Exception{
		User result = repo.detail(userId);
		
		return result;
	}
	
	@Override
	public int update(User user) throws Exception{
		int updateSuccess = repo.update(user);
		
		return updateSuccess;
	}
	
	@Override
	public int delete(int userId) throws Exception{
		int deleteSuccess = repo.delete(userId);
		
		return deleteSuccess;
	}
	
	///////////////////////////////////////////////////
	
	@Override
	public List<Integer> userCategoryList(int userId) throws Exception{

		List<Integer> list = repo.userCategoryList(userId);
		
		return list;
	}
	
	@Override
	public int updateUserCategory(List<Integer> previusUserCategoryList, List<Integer> newUserCategoryList, int userId) throws Exception{
		Collections.sort(previusUserCategoryList);
		Collections.sort(newUserCategoryList);
		
		
		for (int i = 0, size = newUserCategoryList.size(); i < size; i++) {
			if(previusUserCategoryList.contains(newUserCategoryList.get(i))){
				for (int j = 0; j < previusUserCategoryList.size(); j++) { 
					if(previusUserCategoryList.get(j) == newUserCategoryList.get(i)) {
						previusUserCategoryList.remove(j);
					}
				}
				newUserCategoryList.remove(i);
				i--;
				size--;
			}		
		}
		
		int result = 0;
		for (int i = 0; i < newUserCategoryList.size(); i++) {
			System.out.println(newUserCategoryList.get(i) + " " + userId);
			result += repo.insertUserCategory(newUserCategoryList.get(i), userId);
		}
		for (int i = 0; i < previusUserCategoryList.size(); i++) {
			result += repo.deleteUserCategory(previusUserCategoryList.get(i), userId);
		}

		return result;
	}
}
