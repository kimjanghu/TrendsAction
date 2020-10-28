package com.action.trends.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.dto.User;
import com.action.trends.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ERROR = "error";
	private static final String WRONG = "wrong";

	@Autowired
	UserService userService;

	@ApiOperation(value = "닉네임 중복을 확인한다.", response = Map.class)
	@GetMapping("/checknickname/{nickname}")
	public ResponseEntity<Map<String, Object>> checknickname(@PathVariable String nickname) throws Exception {
		logger.debug("닉네임 중복 확인 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			int checknick = userService.checknickname(nickname);
			
			if(checknick == 1) {
				map.put("status", false);
				map.put("message", "중복되는 닉네임이 존재합니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				map.put("status", true);
				map.put("message", "사용 가능한 닉네임 입니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
			
			
		}catch(Exception e) {
			map.put("Error", ERROR);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "해당 유저아이디에 대한 유저정보를 반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> userInfo(int userId) throws Exception{
		logger.debug("해당 유저 정보 반환 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			User userData = userService.detail(userId);
			if(userData != null) {
				map.put("status", true);
				map.put("message", "유저정보 조회 성공하였습니다");
				map.put("data", userData);
			}else {
				map.put("status", false);
				map.put("message", "유저정보 조회 실패하였습니다");
			}
		}catch(Exception e){
			map.put("Error", ERROR);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 유저아이디에 대한 유저정보를 수정한다.", response = Map.class)
	@PutMapping
	public ResponseEntity<Map<String, Object>> userUpdate(@RequestBody User user) throws Exception{
		logger.debug("해당 유저 정보 수정 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			int updateSuccess = userService.update(user);
			if(updateSuccess == 1) {
				map.put("status", true);
				map.put("message", "유저정보 수정 성공했습니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}else {
				map.put("status", false);
				map.put("message", "유저정보 수정 실패했습니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}catch(Exception e) {
			map.put("Error", ERROR);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "해당 유저아이디에 대한 정보를 삭제한다.", response = Map.class)
	@DeleteMapping("{userId}")
	public ResponseEntity<Map<String, Object>> userDelete(@PathVariable int userId) throws Exception{
		logger.debug("해당 유저 정보 삭제 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			int deleteSuccess = userService.delete(userId);

			if(deleteSuccess == 1) {
				map.put("status", true);
				map.put("message", "유저정보 삭제 성공했습니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
				
			}else {
				map.put("status", false);
				map.put("message", "유저정보 삭제 실패했습니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}catch(Exception e) {
			map.put("Error", ERROR);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
						/*여기부턴 유저 개인의 관심카테고리 CURD 작성*/
	
	/////////////////////////////////////////////////////////////////////////////////
	
	@ApiOperation(value = "유저 개인의 관심 카테고리를 조회힌다.", response = Map.class)
	@GetMapping("/category/{userId}")
	public ResponseEntity<Map<String, Object>> userCategoryInfo(@PathVariable int userId) throws Exception {
		logger.debug("유저 개인의 관심 카테고리를 조회 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			List<Integer> userCategoryList = userService.userCategoryList(userId);
			
			map.put("data", userCategoryList);
			map.put("status", true);
			map.put("message", "유저 개인의 관심 카테고리 목록 조회 성공했습니다.");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		}catch(Exception e) {
			
			map.put("Error", ERROR);
			map.put("status", false);
			map.put("message", "유저 개인의 관심 카테고리 목록 조회 실패했습니다.");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@ApiOperation(value = "유저 개인의 관심 카테고리를 등록 및 삭제힌다.", response = Map.class)
	@PostMapping("/category")
	public ResponseEntity<Map<String, Object>> writeUserCategory(@RequestBody List<Integer> categoryIdList, int userId) throws Exception {
		logger.debug("유저 개인의 관심 카테고리를 등록 및 삭제 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
		
			List<Integer> previusUserCategoryList = userService.userCategoryList(userId);
			List<Integer> newUserCategoryList = categoryIdList;
			
			userService.updateUserCategory(previusUserCategoryList, newUserCategoryList, userId);
		
			map.put("status", true);
			map.put("message", "유저 개인의 관심 카테고리를 등록 및 삭제 성공했습니다.");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "유저 개인의 관심 카테고리를 등록 및 삭제 실패했습니다.");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}
