package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentDao {
	
	public List<Comment> selectByVideoId(String videoId, int startNum);
	
	public int insertComment(Comment comment);
	
	public int updateComment(Comment comment);
	
	public int deleteComment(int commentId);
	
	public int deleteAllComment(String videoId);
	
	public int countComment(String videoId);
	
	public int countChild(int commentId);
	
//	public List<Comment> searchComment(Map<String, String> params);
}
