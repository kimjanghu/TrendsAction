package com.ssafy.user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.dto.User;
import com.ssafy.user.repository.UserRepository;

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
}
