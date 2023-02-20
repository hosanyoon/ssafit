package com.ssafy.ssafit.controller;

import java.io.UnsupportedEncodingException;
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

import com.ssafy.ssafit.exception.UserIdDuplicationExcepion;
//import com.ssafy.ssafit.exception.UserIdDuplicationExcepion;
import com.ssafy.ssafit.exception.UserNotFoundException;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userapi")
public class UserRestController {
	@Autowired
	UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@PostMapping("/login")
	@ApiOperation(value = "로그인을 위해 id와 password가 일치하는 하는 사용자 정보를 반환한다.")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		HashMap<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {
			if (user.getPassword().equals(userService.getUserById(user.getId()).getPassword())) {
				user = userService.getUserById(user.getId());
//				user.setPassword("");
				result.put("access-token", jwtUtil.createToken("id", user.getId()));
				result.put("message", SUCCESS);
//				user = userService.getUserById(user.getId());
//				user.setPassword("");
				result.put("loginUser", user);
				status = HttpStatus.ACCEPTED;
			} else {
				result.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@GetMapping("/users")
	@ApiOperation(value = "모든 유저목록 반환")
	public ResponseEntity<?> getUserAll() {
		List<User> users = userService.getUserAll();
		if (users != null && users.size() > 0) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/users")
	@ApiOperation(value = "새로운 유저 등록")
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		try {
			System.out.println("들어온 유저");
			System.out.println(user);
			int result1 = userService.insertUser(user);
			int result2 = userService.insertUserBody(user);
			return new ResponseEntity<Integer>(result1+result2, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new UserIdDuplicationExcepion("아이디 중복");
		}
	}

	@GetMapping("/users/{id}")
	@ApiOperation(value = "id에 해당하는 유저정보 반환를 반환하다. ")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new UserNotFoundException(id + "의 게시글은 없다 .");
		}
	}

	@PutMapping("/users/{id}")
	@ApiOperation("id에 해당하는 사용자 정보를 수정한다.")
	public ResponseEntity<?> modifyUser(@RequestBody User user, @PathVariable String id) {
		int result = userService.modifyUser(user);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	@ApiOperation("id에 해당하는 사용자 정보를 삭제한다. ")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		int result = userService.deleteUser(id);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/recodes/{id}")
	@ApiOperation("id에 해당하는 유저 운동기록 반환")
	public ResponseEntity<?> getRecodes(@PathVariable String id) {
		List<User> users = userService.getRecords(id);
		if(users != null && users.size()>0) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	
	}
	@PostMapping("/recodes/{id}")
	@ApiOperation("id에 해당하는 유저 최근기록 등록")
	public ResponseEntity<?> insertRecode(@RequestBody User user, @PathVariable String id) {
			int result = userService.insertUserBody(user);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/like/{id}")
	@ApiOperation("id에 해당하는 유저 찜목록 반환")
	public ResponseEntity<?> getLikedVideos(@PathVariable String id) {
		List<Video> likeVideos = userService.getLikedVideos(id);
		System.out.println(likeVideos);
		if(likeVideos != null && likeVideos.size()>0) {
			System.out.println("가즈아");
			return new ResponseEntity<List<Video>>(likeVideos, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/like/{id}")
	@ApiOperation("id에 해당하는 유저 찜목록 등록")
	public ResponseEntity<?> insertLikedVideo(@RequestBody User user, @PathVariable String id) {
		int result = userService.insertLikedVideo(id, user.getLikedVideo());
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	// 찜목록 (user_like)에서 삭제하기
	@DeleteMapping("/like/{id}")
	@ApiOperation("id에 해당하는 유저 찜목록 삭제")
	public ResponseEntity<?> deleteLikedVideo(@RequestBody User user, @PathVariable String id) {
		System.out.println("아이디ㅣㅣㅣㅣㅣㅣㅣㅣㅣ"+id);
		System.out.println(user);
		int result = userService.deleteLikedVideo(id, user.getLikedVideo());
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
}
