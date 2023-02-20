package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoDao {

	public Video selectVideoById(String videoId);
	
	public List<Video> selectMostViewedVideos();
	
	public int insertVideo(Video video);
	
	public int updateVideo(Video video);
	
	public int deleteVideo(String videoId);
	
	public List<Video> selectVideosByPart(int videoPart);
	
	public List<Video> selectVideosByTitle(String title);
	
	public List<Video> selectLikedVideos(String userId);
	
	public int countLikedVideo(@Param("videoId")String videoId, @Param("userId")String userId);
	
}
