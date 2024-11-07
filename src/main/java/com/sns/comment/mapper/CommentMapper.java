package com.sns.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
	
	public void insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
}
