package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.Comment;

public interface CommentService {
	List<Comment> readByTrendId(int trendId);
}
