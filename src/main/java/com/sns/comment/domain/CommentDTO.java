package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

// 댓글 1개
@Getter
@Setter
public class CommentDTO {
	// 댓글 1개
	private Comment comment;
	
	// 댓글쓴이 1개
	private UserEntity user;
}
