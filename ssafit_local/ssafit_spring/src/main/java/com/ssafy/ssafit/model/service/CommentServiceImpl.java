package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> getCommentListByVideoId(String videoId, int pageNum, int displayedArticle) {
		int startNum = (pageNum-1) * displayedArticle;
		List<Comment> cList = commentDao.selectByVideoId(videoId, startNum);
		return cList;
	}

	@Override
	public int deleteCommentList(String videoId) {		
		return commentDao.deleteAllComment(videoId);
	}
	

//	@Override
//	public List<Comment> searchComment(Map<String, String> params) {
//		return commentDao.searchComment(params);
//	}

	@Override
	public int writeComment(Comment comment) {
		commentDao.insertComment(comment);
		if(comment.getParent() == 0) {
			comment.setParent(comment.getCommentId());
			commentDao.updateComment(comment);
		}
		return 1;
	}

	@Override
	public int modifyComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		int childNum = commentDao.countChild(comment.getCommentId());
		if(childNum <= 1) return commentDao.deleteComment(comment.getCommentId());
		else {
			comment.setContent("삭제된 댓글입니다.");
			comment.setWriter("Empty");
			return modifyComment(comment);
		}
	}

	@Override
	public int countComment(String videoId) {
		return commentDao.countComment(videoId);
	}

	
}
