package com.action.trends.dto;

import java.util.List;

public class UserStringCategory {
	private int id;
	private String email;
	private String nickname;
	private String profile;
	private List<String> categoryList;

	public UserStringCategory() {
	}

	public UserStringCategory(int id, String email, String nickname, String profile, List<String> categoryList) {
		super();
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.profile = profile;
		this.categoryList = categoryList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	@Override
	public String toString() {
		return "UserStringCategory [id=" + id + ", email=" + email + ", nickname=" + nickname + ", profile=" + profile
				+ ", categoryList=" + categoryList + "]";
	}

}
