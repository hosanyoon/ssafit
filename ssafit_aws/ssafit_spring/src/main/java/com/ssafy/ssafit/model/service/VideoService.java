package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dto.Video;

@Service
public interface VideoService {

	public Video getVideoById(String videoId);

	public List<Video> getMostViewedVideos();

	public int insertVideo(Video video);

	public int modifyVideo(Video video);

	public int deleteVideo(String videoId);

	public List<Video> getVideosByPart(int videoPart);

	public List<Video> getVideosByTitle(String title);

	public boolean isLikedVideo(String videoId,String userId);

	public int modifyViewCnt(String videoId);
}
