package com.ssafy.ssafit.model.dto;

public class Comment {
	private int commentId;
	private String videoId;
	private String writer;
	private String content;
	private int parent;
	private String registDay;
	
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getRegistDay() {
		return registDay;
	}
	public void setRegistDay(String registDay) {
		this.registDay = registDay;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", videoId=" + videoId + ", writer=" + writer + ", content="
				+ content + ", parent=" + parent + ", registDay=" + registDay + "]";
	}	
}
