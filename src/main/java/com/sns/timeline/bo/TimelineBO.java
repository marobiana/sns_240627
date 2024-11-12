package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TimelineBO {

	private final PostBO postBO;
	private final UserBO userBO;
	private final CommentBO commentBO;
	private final LikeBO likeBO;
	
	// input: 로그인 된 사람 userId(비로그인도 가능 null도 가능)
	// output: List<CardDTO>
	public List<CardDTO> generateCardList(Integer userId) {
		List<CardDTO> cardList = new ArrayList<>();
		
		// 글 목록 가져옴
		List<PostEntity> postList = postBO.getPostList();
		
		// 글 1개 => CardDTO로 변환    반복문
		for (PostEntity postEntity : postList) {
			CardDTO card = new CardDTO();
			
			// 글
			card.setPost(postEntity);
			
			// 글쓴이
			//int userId = postEntity.getUserId();
			card.setUser(userBO.getUserEntityById(postEntity.getUserId()));
			
			// 댓글들
			// commentBO => commnetDTO list
			card.setCommentList(commentBO.generateCommentList(postEntity.getId()));
			
			// 좋아요 개수
			card.setLikeCount(likeBO.getLikeCountByPostId(postEntity.getId()));
			
			// 하트 채울지 말지 여부
			card.setFilledLike(likeBO.filledLikeByPostIdUserId(postEntity.getId(), userId));
			
			//!!!!!! list에 DTO 넣기
			cardList.add(card);
		}
		
		return cardList;
	}
}
