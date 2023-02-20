package com.ssafy.ssafit.model.dto;

public class Video {
	private String videoId;
	private String videoTitle;
	private int videoPart;
	private int viewCnt;
	
	
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public int getExercisePart() {
		return videoPart;
	}
	public void setExercisePart(int videoPart) {
		this.videoPart = videoPart;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoTitle=" + videoTitle + ", videoPart=" + videoPart + ", viewCnt="
				+ viewCnt + "]";
	}
	
	
}
