package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;

	// 컨트롤러한테
	// input: loginId
	// output: UserEntity or null
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}

	// input: 파라미터4개
	// output: UserEntity
	public UserEntity addUser(String loginId, String password, String name, String email) {

		// 실질적으로 오류 발생시 null return 안 됨. (try-catch로 예외처리 해야함)
		return userRepository
				.save(UserEntity.builder()
						.loginId(loginId)
						.password(password)
						.name(name)
						.email(email)
						.build());
	}
}
