package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentRestController {

	private final CommentBO commentBO;
	
	// DI(Dependency Injection)
//	public CommentRestController(CommentBO commentBO) {
//		this.commentBO = commentBO;
//	}
	
	@PostMapping("/create") 
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		// 로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		
		// insert
		commentBO.addComment(postId, userId, content);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
