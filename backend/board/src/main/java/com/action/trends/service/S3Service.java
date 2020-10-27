package com.action.trends.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	public void uploadFile(final MultipartFile multipartFile, int userId);
}