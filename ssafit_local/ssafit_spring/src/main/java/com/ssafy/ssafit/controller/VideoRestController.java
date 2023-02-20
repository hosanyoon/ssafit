package com.ssafy.ssafit.controller;

import java.util.List;

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

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.VideoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/videoapi")
public class VideoRestController {
	
	@Autowired
	VideoService videoService;
	
	
	@GetMapping("/videos/{videoId}")
	@ApiOperation("id에 해당하는 비디오 반환")
	public ResponseEntity<?> getVideoById(@PathVariable String videoId) {
		videoService.modifyViewCnt(videoId);
		return new ResponseEntity<Video>(videoService.getVideoById(videoId), HttpStatus.OK);
	}
	
	@PostMapping("/videos/{videoId}")
	@ApiOperation("새로운 비디오 등록")
	public ResponseEntity<?> InsertVideo(@RequestBody Video video, @PathVariable String videoId) {
		int result = videoService.insertVideo(video);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@PutMapping("/videos/{videoId}")
	@ApiOperation("id에 해당하는 비디오 수정")
	public ResponseEntity<?> modifyVideo(Video video, @PathVariable String videoId) {
		int result = videoService.modifyVideo(video);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/video/{videoId}")
	@ApiOperation("id에 해당하는 비디오 삭제")
	public int deleteVideo(@PathVariable String videoId) {
		return videoService.deleteVideo(videoId);		
	}
	
	@GetMapping("/list/most")
	@ApiOperation("조회순으로 비디오리스트 반환")
	public ResponseEntity<?> getMostViewedVideos() {
		return new ResponseEntity<List<Video>>(videoService.getMostViewedVideos(), HttpStatus.OK);
	}
	
	@GetMapping("/list/part/{videoPart}")
	@ApiOperation("운동부위별로 비디오리스트 반환")
	public ResponseEntity<?> getVideosByPart(@PathVariable int videoPart){
		return new ResponseEntity<List<Video>>(videoService.getVideosByPart(videoPart), HttpStatus.OK);
	}
	
	@GetMapping("/list/like/{userId}")
	@ApiOperation("유저id가 찜한 비디오 리스트 반환")
	public ResponseEntity<?> getLikedVideos(@PathVariable String userId){
		return new ResponseEntity<List<Video>>(videoService.getLikedVideos(userId), HttpStatus.OK);
	}
	
	
}
