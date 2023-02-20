package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;

public interface UserDao {
	int insertUser(User user);
	
	int insertUserBody(User user);

	User selectUserById(String id);
	
	List<User> selectUserAll();

	int deleteUser(String id);
	
	int updateUser(User user);
	
	//유저 운동 현황 기록 가져오기
	List<User> selectRecords(String id);
	//유저 운동 현황 최근값 가져오기 
	User selectLastRecord(String id);
	//찜목록 가져오기 
	List<Video> selectLikedVideos(String id);
	//찜목록 등록하기
	int insertLikedVideo(@Param("id")String id, @Param("likedVideo")String likedVideo);
	//찜록록 삭제하기 
	int deleteLikedVideo(@Param("id")String id, @Param("likedVideo")String likedVideo);
}
