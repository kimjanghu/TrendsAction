package com.action.trends.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.dto.User;
import com.action.trends.dto.UserStringCategory;
import com.action.trends.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@ApiOperation(value = "닉네임 중복을 확인한다.", response = Map.class)
	@GetMapping("/checknickname/{nickname}")
	public ResponseEntity<Map<String, Object>> checknickname(@PathVariable String nickname) throws Exception {
		logger.debug("닉네임 중복 확인 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			int checknick = userService.checknickname(nickname);

			if (checknick == 1) {
				map.put("status", false);
				map.put("message", "중복되는 닉네임이 존재합니다");

				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				map.put("status", true);
				map.put("message", "사용 가능한 닉네임 입니다");

				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}

		} catch (Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "해당 유저아이디에 대한 유저정보를 반환한다.", response = Map.class)
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> userInfo(@PathVariable int userId) throws Exception {
		logger.debug("해당 유저 정보 반환 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		User userData = userService.detail(userId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		try {
			dataMap.put("id", userData.getId());
			dataMap.put("email", userData.getEmail());
			dataMap.put("nickname", userData.getNickname());
			dataMap.put("profile", userData.getProfile());
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "유저정보 조회 실패하였습니다");
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			dataMap.put("categoryName", userService.userCategoryListAsString(userId));
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "userId로 개인카테고리 목록 이름들을 가져오는데 실패했습니다.");
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		map.put("status", true);
		map.put("message", "유저정보 조회 성공하였습니다");
		map.put("data", dataMap);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		

	}

	@ApiOperation(value = "해당 유저아이디에 대한 유저정보를 수정한다.", response = Map.class)
	@PutMapping
	public ResponseEntity<Map<String, Object>> userUpdate(@RequestBody UserStringCategory user) throws Exception {
		logger.debug("해당 유저 정보 수정 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			int updateUserdataSuccess = userService.update(user);
			if (updateUserdataSuccess == 1) {
				map.put("status", true);
				map.put("message", "유저정보 수정(카테고리 제외) 성공했습니다");
			} else {
				map.put("status", false);
				map.put("message", "유저정보 수정(카테고리 제외)에서 실패했습니다");

				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			List<String> previusUserCategoryList = userService.userCategoryListAsString(user.getId());
			List<String> newUserCategoryList = user.getCategoryList();

			int[] updateCategoryListSuccess = new int[2];

			updateCategoryListSuccess = userService.updateUserCategory(previusUserCategoryList, newUserCategoryList,
					user.getId());

			if (updateCategoryListSuccess[0] == 1 || updateCategoryListSuccess[1] == 1) {
				map.put("status", true);
				map.put("message", "유저정보 수정(카테고리 포함) 성공했습니다");
			} else {
				map.put("status", true);
				map.put("message", "유저정보 수정(카테고리 포함) 성공했으나 수정한 것은 없습니다");
			}

			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "해당 유저아이디에 대한 정보를 삭제한다.", response = Map.class)
	@DeleteMapping("{userId}")
	public ResponseEntity<Map<String, Object>> userDelete(@PathVariable int userId) throws Exception {
		logger.debug("해당 유저 정보 삭제 - 호출");
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			System.out.println("진입");
			int deleteSuccess = userService.delete(userId);
			System.out.println(deleteSuccess);
			if (deleteSuccess == 1) {
				map.put("status", true);
				map.put("message", "유저정보 삭제 성공했습니다");

				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

			} else {
				map.put("status", false);
				map.put("message", "유저정보 삭제 실패했습니다");

				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			map.put("error", error.toString());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
