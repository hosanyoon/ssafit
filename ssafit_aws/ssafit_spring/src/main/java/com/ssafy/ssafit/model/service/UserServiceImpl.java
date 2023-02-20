package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}
	
	@Override
	public int insertUserBody(User user) {
		return userDao.insertUserBody(user);
	}

	@Override
	public User getUserById(String id) {
		return userDao.selectUserById(id);
	}
	

	@Override
	public List<User> getUserAll() {
		return userDao.selectUserAll();
	}


	@Override
	public int deleteUser(String id) {
		return userDao.deleteUser(id);
	}

	@Override
	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}


	@Override
	public List<User> getRecords(String id) {
		return userDao.selectRecords(id);
	}

	@Override
	public User getLastRecord(String id) {
		return userDao.selectLastRecord(id);
	}

	@Override
	public List<Video> getLikedVideos(String id) {
		return userDao.selectLikedVideos(id);
	}

	@Override
	public int insertLikedVideo(String id, String videoId) {		
		return userDao.insertLikedVideo(id, videoId);
	}

	@Override
	public int deleteLikedVideo(String id, String videoId) {
		return userDao.deleteLikedVideo(id, videoId);
	}
	
}
