package com.action.trends.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.Board;

@Mapper
public interface BoardMapper {
	public List<Board> getBoardList(int userId);
}
