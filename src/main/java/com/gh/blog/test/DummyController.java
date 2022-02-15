package com.gh.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gh.blog.model.RoleType;
import com.gh.blog.model.User;
import com.gh.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
	}
}
