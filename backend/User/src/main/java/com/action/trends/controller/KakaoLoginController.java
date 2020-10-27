package com.action.trends.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.action.trends.service.KakaoAPIService;
import com.action.trends.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
 
@Controller
public class KakaoLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ERROR = "error";
	private static final String WRONG = "wrong";
	
	@Autowired
	KakaoAPIService kakaoAPIService;
	
    @RequestMapping(value="/")
    public String index() {
        
        return "index";
    }
    
    @ApiOperation(value = "카카오 로그인 후 토큰 response", response = String.class)
    @RequestMapping(value="/login/kakao")
    public String login(@RequestParam("code") String code) throws Exception{
    	
    	logger.debug("카카오 로그인 후 토큰 response - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		String JWT_token = null;
		HashMap<String, Object> userInfo = null;
		try {
	    	//Front에서 redirect 질의를 통해 요청코드를 받았었고 이를 백으로 전달해옴    	
	        System.out.println("code : " + code);
	        //요청코드를 바탕으로 access_Token을 kakao에 요청함
	        String access_Token = kakaoAPIService.getAccessToken(code);
	        System.out.println("controller access_token : " + access_Token);
	        
	        //access_Token을 기반으로 사용자 정보를 Json 형식으로 쭉 가져옴
	        userInfo = kakaoAPIService.getUserInfo(access_Token);
	        System.out.println("login Controller : " + userInfo);
	        
	        if(kakaoAPIService.isUserInfoExist(userInfo.get("email").toString()) == 1) {
//				map.put("message", "로그인 성공 및 새로운 유저정보 생성 하였습니다");
	        }else {
//				map.put("message", "로그인 성공 했습니다.");
	        }
	        
	        
	        //토큰 생성
	        new JWTUtil();
			JWT_token = JWTUtil.createJWTToken(userInfo.get("email").toString());
			
		}catch(Exception e) {
//			map.put("Error", ERROR);
		}
		System.out.println("JWT_token : " + JWT_token);
		
		if(JWTUtil.verifyToken(JWT_token).equals(userInfo.get("email").toString())) {
			System.out.println("토큰 검증이 성공적으로 끝났습니다");
		}
		return "redirect:http://localhost:8880/api?token=" + JWT_token; // abc408.p.ssafy.io
//		return "index";
    }
}