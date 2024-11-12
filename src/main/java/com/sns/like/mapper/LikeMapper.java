package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	// return(output): 1 존재(like 되어있음)     0 없음(like 안 되어 있음)
	public int selectLikeCountByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	// input: postId     output:좋아요 개수(int)
	public int selectLikeCountByPostId(int postId);
	
	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public int deleteLikeByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
}





