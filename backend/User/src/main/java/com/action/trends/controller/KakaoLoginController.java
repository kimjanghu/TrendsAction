package com.action.trends.controller;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.action.trends.dto.User;
import com.action.trends.service.KakaoAPIService;
import com.action.trends.util.JWTUtil;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("login")
public class KakaoLoginController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ERROR = "error";
	private static final String WRONG = "wrong";

	@Autowired
	KakaoAPIService kakaoAPIService;
	
	@ApiOperation(value = "카카오 로그인 후 토큰 response", response = Map.class)
	@GetMapping("/kakao{code}")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("code") String code) throws Exception {
		System.out.println(code);
		logger.debug("카카오 로그인 후 토큰 response - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		String JWT_token = null;
		HashMap<String, Object> userInfo = null;
		User userData = null;
		
//		System.out.println("code :" + " " +  code);
//		
//		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		try {

			String access_Token = kakaoAPIService.getAccessToken(code);	

			userInfo = kakaoAPIService.getUserInfo(access_Token);

			new JWTUtil();
			JWT_token = JWTUtil.createJWTToken(userInfo.get("email").toString());

			
			
			int firstLogin = kakaoAPIService.isUserInfoExist(userInfo.get("email").toString());
			
			
			userData = kakaoAPIService.userDetail(userInfo.get("email").toString());

			map.put("token", JWT_token);
			map.put("userId", userData.getId());
			map.put("nickname", userData.getNickname());
			map.put("success", SUCCESS);

			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}