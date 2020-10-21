package com.action.trends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Comment;
import com.action.trends.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository repository;

	@Override
	public List<Comment> readByTrendId(int trendId) {
		return repository.readByTrendId(trendId);
	}

}
