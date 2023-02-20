package com.ssafy.ssafit.model.dto;

import java.util.Date;

public class User {

	private String id; // 아이디
	private String password; // 비밀번호
	private int age; // 나이
	private int sex; // 남자 true 여자 false
	private int height;	//키
	private int preference; //헬스장 , 필라테스, 크로스핏, 홈트
	private int weight; //몸무게
	private int recent; //마지막 운동부위
	private Date date; //마지막 운동날짜
	private String likedVideo; //찜영상 
	private String image = "https://d38sfifral62ij.cloudfront.net/noImage.png";
	
	//생성자랑 toString 나중에 
	
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", age=" + age + ", sex=" + sex + ", height=" + height
				+ ", preference=" + preference + ", weight=" + weight + ", recent=" + recent + ", date=" + date
				+ ", likedVideo=" + likedVideo + ", image=" + image + "]";
	}
	
	public User() {
		super();
	}

	public User(String id, String password, int age, int sex, int height, int preference, int weight, int recent,
			Date date, String likedVideo, String image) {
		super();
		this.id = id;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.height = height;
		this.preference = preference;
		this.weight = weight;
		this.recent = recent;
		this.date = date;
		this.likedVideo = likedVideo;
		this.image = image;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	//get, set
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getPreference() {
		return preference;
	}
	public void setPreference(int preference) {
		this.preference = preference;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getRecent() {
		return recent;
	}
	public void setRecent(int recent) {
		this.recent = recent;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLikedVideo() {
		return likedVideo;
	}
	public void setLikedVideo(String likedVideo) {
		this.likedVideo = likedVideo;
	}
	
	
	
}