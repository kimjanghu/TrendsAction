package com.action.trends.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface S3UploadMapper {
	void uploadProfileImage(int userId, String uniqueFileName);
	void uploadBoardProfileImage(int boardId, String uniqueFileName);
}
