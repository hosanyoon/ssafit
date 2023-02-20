package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao videoDao;

	@Autowired
	CommentDao commentDao;
	
	@Override
	public Video getVideoById(String videoId) {
		return videoDao.selectVideoById(videoId);
	}
	
	@Override
	public List<Video> getMostViewedVideos() {
		return videoDao.selectMostViewedVideos();
	}

	@Override
	public int insertVideo(Video video) {
		return videoDao.insertVideo(video);
	}
	
	@Override
	public int modifyVideo(Video video) {
		return videoDao.updateVideo(video);
	}
	
	@Override
	public int deleteVideo(String videoId) {
		commentDao.deleteAllComment(videoId);
		return videoDao.deleteVideo(videoId);
	}
	
	@Override
	public List<Video> getVideosByPart(int videoPart) {
		return videoDao.selectVideosByPart(videoPart);
	}

	@Override
	public List<Video> getVideosByTitle(String title) {
		return videoDao.selectVideosByTitle(title);
	}
	
	@Override
	public int modifyViewCnt(String videoId) {
		Video video = videoDao.selectVideoById(videoId);
		video.setViewCnt(video.getViewCnt() + 1);
		return videoDao.updateVideo(video);
	}

	@Override
	public boolean isLikedVideo(String videoId, String userId) {
		if(videoDao.countLikedVideo(videoId, userId)==0) {
			return false;
		}else return true;
	}

	
}
