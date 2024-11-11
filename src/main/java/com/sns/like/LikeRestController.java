package com.sns.like;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {

	// GET:  /like?postId=3      @RequestParam("postId")
	// GET:  /like/3   /like/{postId}  @PathVariable(name="postId")
	@GetMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable(name = "postId") int postId,
			HttpSession session) {
		
		// 로그인 여부 확인
		
		// toggle 요청 -> BO
		
		// 응답값
	}
}
