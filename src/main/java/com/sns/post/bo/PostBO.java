package com.sns.post.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostBO {
	
	private PostRepository postRepository;
	private CommentBO commentBO;
	private LikeBO likeBO;
	private FileManagerService fileManager;

	// input:X
	// output:List<PostEntity>
	public List<PostEntity> getPostList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	// input: 파라미터들 output:PostEntity
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {

		// 업로드 후 imagePath를 받아옴
		String imagePath = fileManager.uploadFile(file, userLoginId);

		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	// input: postId
	// output: X
	@Transactional
	public void deletePostByPostId(int postId) {
		// 1. 삭제할 글이 있나? => 이미지 path
		PostEntity post = postRepository.findById(postId).orElse(null);
		if (post == null) {
			log.info("[글 삭제] post is null. postId:{}", postId);
			return;
		}
		
		// 2. 글 삭제 - postId
		postRepository.delete(post); // 엔티티를 넘기지만 id(pk)로 삭제해줌
		
		// 3. 댓글 삭제 - postId
		commentBO.deleteCommentListByPostId(postId);
		
		// 4. 좋아요 삭제 - postId
		likeBO.deleteLikeListByPostId(postId);
		
		// 5. 이미지 파일 삭제 - 1번에서 가져온 postEntity -> 이미지path 
		fileManager.deleteFile(post.getImagePath());
	}
}



