package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.service.CommentService;
import com.ssafy.ssafit.util.PageNavigation;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/commentapi")
public class CommentRestController {

	@Autowired
	CommentService commentService;
	

	//댓글 작성
	@PostMapping("/comment")
	@ApiOperation(value = "댓글을 전송 받아 db에 작성한다.")
	public ResponseEntity<?> writeComment(@RequestBody Comment comment) {
		commentService.writeComment(comment);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	//댓글 수정
	@PutMapping("/comment")
	@ApiOperation(value = "댓글을 전송 받아 같은 comment_id를 가진 댓글을 db에서 찾아 update한다.")
	public ResponseEntity<?> modifyComment(@RequestBody Comment comment){
		commentService.modifyComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping("/comment")
	@ApiOperation(value = "comment를 받아 db에서 해당 댓글을 찾고 자식이 없을시 삭제, 있을 시 작성자 Empty, 내용: 삭제된 댓글입니다로 수정한다.")
	public ResponseEntity<?> deleteComment(@RequestBody Comment comment){
		commentService.deleteComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//댓글 일괄 삭제
	@DeleteMapping("/list/{videoId}")
	@ApiOperation(value = "videoId를 받아 해당 videoId를 가지고 있는 모든 댓글을 db에서 삭제한다.")
	public ResponseEntity<?> deleteCommentList(@PathVariable String videoId){		
		commentService.deleteCommentList(videoId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//댓글 목록 반환
	@GetMapping("/list/{videoId}/pages/{pageNum}")
	@ApiOperation(value = "videoId와 이동할 페이지를 받아 해당 videoId를 가지고 있는 모든 댓글 중 해당 page에 표시할 부분을 반환한다.")
	public ResponseEntity<?> getCommentList(@PathVariable String videoId, @PathVariable int pageNum) {
		int totalArticleCount = commentService.countComment(videoId);
		PageNavigation pageNav = new PageNavigation(totalArticleCount, pageNum);
				
		List<Comment> commentList = commentService.getCommentListByVideoId(videoId, pageNum, pageNav.getDisplayedArticle());
		Map<String, Object> map = new HashMap<>();
		map.put("commentList", commentList);
		map.put("pageNav", pageNav);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
//	@PostMapping("/comment/search")
//	public ResponseEntity<?> searchComment(@RequestParam String mode, @RequestParam String keyword){
//		Map<String, String> params = new HashMap<String, String>();
//		
//		params.put("mode", mode);
//		params.put("keyword", keyword);
//		
//		return new ResponseEntity<List<Comment>>(commentService.searchComment(params), HttpStatus.OK);
//	}
}
