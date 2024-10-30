package com.sns.user.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
	private int id;
	private String loginId;
	private String password;
	private String name;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
