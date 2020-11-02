package com.action.trends.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.action.trends.service.S3Service;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class UploadController {
	@Autowired
	private S3Service s3Service;
	
	// 유저 프로필 사진 업로드
	@PostMapping("/upload/{userId}")
	public ResponseEntity<String> uploadFile(@PathVariable int userId,
			@RequestPart(value = "file") final MultipartFile multipartFile) {
		
		s3Service.uploadFile(multipartFile, userId);
		final String response = "[" + multipartFile.getOriginalFilename() + "] uploaded successfully.";
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
