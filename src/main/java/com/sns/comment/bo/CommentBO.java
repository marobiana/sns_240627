package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentBO {

	private final CommentMapper commentMapper;
	private final UserBO userBO;
	
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}
	
	// input: 글번호
	// output: List<CommentDTO>
	public List<CommentDTO> generateCommentList(int postId) {
		List<CommentDTO> commentDTOList = new ArrayList<>();
		
		// 글에 해당하는 댓글들을 가져온다.(List<Comment>)
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 => Comment->CommentDTO   => list에 담기
		for (Comment comment : commentList) {
			CommentDTO commentDTO = new CommentDTO();
			
			// 댓글1개
			commentDTO.setComment(comment);
			
			// 댓글쓴이
			commentDTO.setUser(userBO.getUserEntityById(comment.getUserId()));
			
			//!!!!!! List에 DTO 담기
			commentDTOList.add(commentDTO);
		}
		
		return commentDTOList;
	}
	
	public void deleteCommentById(int commentId) {
		commentMapper.deleteCommentById(commentId);
	}
}





