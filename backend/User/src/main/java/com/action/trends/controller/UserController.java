package com.action.trends.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import com.action.trends.util.JWTUtil;

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
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}else {
				map.put("status", true);
				map.put("message", "사용 가능한 닉네임 입니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
			
			
		}catch(Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "해당 유저아이디에 대한 유저정보를 반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> userInfo(@RequestHeader("token") String token) throws Exception{
		logger.debug("해당 유저 정보 반환 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		String decodeEmail = null;
		User userData = null;
		new JWTUtil();
		try {
			decodeEmail = JWTUtil.verifyToken(token);
			userData = userService.detailByEmail(decodeEmail);
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "토큰 디코딩 및 인증에 실패했습니다.");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		
		try{

			if(userData != null) {
				map.put("status", true);
				map.put("message", "유저정보 조회 성공하였습니다");
				map.put("data", userData);
			}else {
				map.put("status", false);
				map.put("message", "유저정보 조회 실패하였습니다");
			}
			System.out.println("flag3");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@ApiOperation(value = "해당 유저아이디에 대한 유저정보를 수정한다.", response = Map.class)
	@PutMapping
	public ResponseEntity<Map<String, Object>> userUpdate(@RequestBody User user, @RequestHeader("token") String token) throws Exception{
		logger.debug("해당 유저 정보 수정 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		new JWTUtil();
		if(!JWTUtil.verifyToken(token).equals(user.getEmail())) {
			
			System.out.println("토큰인증 실패");
			
			map.put("status", false);
			map.put("message", "토큰 인증 실패했습니다..");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}else {
			System.out.println("토큰 인증 성공");
		}
		
		try {
			int updateUserdataSuccess = userService.update(user);
			if(updateUserdataSuccess == 1) {
				map.put("status", true);
				map.put("message", "유저정보 수정(카테고리 제외) 성공했습니다");
				
				
			}else {
				map.put("status", false);
				map.put("message", "유저정보 수정(카테고리 제외)에서 실패했습니다");
				
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			List<Integer> previusUserCategoryList = userService.userCategoryList(user.getId());
			List<Integer> newUserCategoryList = user.getCategoryList();
			
			int[] updateCategoryListSuccess = new int[2];
			updateCategoryListSuccess = userService.updateUserCategory(previusUserCategoryList, newUserCategoryList, user.getId());
			
			if(updateCategoryListSuccess[0] == 1 || updateCategoryListSuccess[1] == 1) {
				map.put("status", true);
				map.put("message", "유저정보 수정(카테고리 포함) 성공했습니다");
			}else {
				map.put("status", true);
				map.put("message", "유저정보 수정(카테고리 포함) 성공했으나 수정한 것은 없습니다");
			}
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		}catch(Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "해당 유저아이디에 대한 정보를 삭제한다.", response = Map.class)
	@DeleteMapping("{userId}")
	public ResponseEntity<Map<String, Object>> userDelete(@PathVariable int userId, @RequestHeader("token") String token) throws Exception{
		logger.debug("해당 유저 정보 삭제 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		
		new JWTUtil();
		if(!JWTUtil.verifyToken(token).equals(userService.detail(userId).getEmail())) {
			
			System.out.println("토큰인증 실패");
			
			map.put("status", false);
			map.put("message", "토큰 인증 실패했습니다..");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}else {
			System.out.println("토큰 인증 성공");
		}
		
		try {
			System.out.println("진입");
			int deleteSuccess = userService.delete(userId);
			System.out.println(deleteSuccess);
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
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
