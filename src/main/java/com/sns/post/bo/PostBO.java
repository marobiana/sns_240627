package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;

	// input:X
	// output:List<PostEntity>
	public List<PostEntity> getPostList() {
		return postRepository.findByOrderByIdDesc();
	}
}
