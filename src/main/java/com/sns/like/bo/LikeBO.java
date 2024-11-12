package com.sns.like.bo;

import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeBO {
	
	private final LikeMapper likeMapper;

	// 토글: 좋아요/해제
	// input: postId, userId
	// output: X
	public void toggleLike(int postId, int userId) {
		// 조회 - postId, userId   => 있으면 삭제  , 없으면 추가
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			// 있다 => 삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없다 => 추가
			likeMapper.insertLike(postId, userId);
		}
	}
	
	// 글에 해당하는 좋아요 개수
	// input: 글번호     output: 좋아요 개수
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
	// 좋아요 하트를 채울지 말지 여부
	// input:글번호, 로그인된사람 or 비로그인(userId)
	// output:boolean(채운다:true, 비운다:false)
	public boolean filledLikeByPostIdUserId(int postId, Integer userId) {
		// 1. 비로그인 => 빈하트
		if (userId == null) {
			return false;
		}
		
		// 2. 로그인 => 누른적 없음 빈하트
		// 3. 로그인 => 누른적 있음 채워진 하트
		int likeCount = likeMapper.selectLikeCountByPostIdUserId(postId, userId);
		return likeCount > 0;
	}
}





