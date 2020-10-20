package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sharer {
	private int userId;
	private String email;
	private String nickname;
	private String profile;
	private String authority;
}
