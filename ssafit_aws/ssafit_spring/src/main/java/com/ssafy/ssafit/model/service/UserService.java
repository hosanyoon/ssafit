package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;

public interface UserService {
	int insertUser(User user);
	
	int insertUserBody(User user);

	User getUserById(String id);
	
	List<User> getUserAll();

	int deleteUser(String id);

	int modifyUser(User user);

	// 유저 운동 현황 기록 가져오기
	List<User> getRecords(String id);
	// 유저 운동 현황 최근값 가져오기
	User getLastRecord(String id);
	// 찜목록 가져오기
	List<Video> getLikedVideos(String id);
	// 찜목록 등록하기
	int insertLikedVideo(String id, String videoId);
	// 찜록록 삭제하기
	int deleteLikedVideo(String id, String videoId);
}
