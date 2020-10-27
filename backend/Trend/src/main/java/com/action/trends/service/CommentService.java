package com.action.trends.service;

import java.util.HashMap;
import java.util.List;

import com.action.trends.dto.Comment;

public interface CommentService {
	List<HashMap<String, Object>> readByTrendId(int trendId);

	void insert(Comment comment);

	void update(Comment comment);

	void delete(int id);
}
