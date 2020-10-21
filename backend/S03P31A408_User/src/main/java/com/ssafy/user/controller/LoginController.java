package com.ssafy.user.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.user.dto.User;

import io.swagger.annotations.ApiOperation;

public class LoginController {

	
	
	
	
	
//	@ApiOperation(value = "로그인 후 성공 여부를 반환한다.")
//	@PostMapping("login") // 로그인
//	public @ResponseBody ResponseEntity<String> login(@RequestBody Map<String, Object> param, Map<String, Object> map) {
//		logger.debug("login - 호출");
//
//		User input = new User();
//		input.setEmail(param.get("email").toString());
//		input.setPassword(param.get("password").toString());
//
//		// Gson gson = new Gson(); // Gson
//		JSONObject object = new JSONObject(); // JSON In Java
//
//		try {
//			User user = service.login(input); // 회원 조회
//
//			if (user != null) { // 회원 존재
//				object.put("userinfo", new JSONObject(user));
//				object.put("success", SUCCESS);
//
//				return new ResponseEntity<String>(object.toString(), HttpStatus.OK); // gson.toJson(map)
//			} else {
//
//				map.put("fail", FAIL);
//				return new ResponseEntity<String>(object.toString(), HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("error", ERROR);
//			return new ResponseEntity<String>(object.toString(), HttpStatus.NOT_ACCEPTABLE);
//		}
//	}
}
