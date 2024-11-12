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
	
}





