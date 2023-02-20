package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dto.Comment;

@Service
public interface CommentService {
	public List<Comment> getCommentListByVideoId(String videoId, int pageNum, int displayedArticle);
	
	public int deleteCommentList(String videoId);
	
//	public List<Comment> searchComment(Map<String, String> params);
	
	public int writeComment(Comment comment);
	
	public int modifyComment(Comment comment);
	
	public int deleteComment(Comment comment);
	
	public int countComment(String videoId);	
	
}
