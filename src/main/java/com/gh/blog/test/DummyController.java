package com.gh.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gh.blog.model.RoleType;
import com.gh.blog.model.User;
import com.gh.blog.repository.UserRepository;

//html파일이 아니라 data를 return해주는 controller = restcontroller
@RestController
public class DummyController {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//{id}주소로 파라미터를 전달 받을 수 있음.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// findById란
		// user /4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null 이 됨.
		// 그럼 return null이 리턴이 되니 문제가 될 수 있으니
		// Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!!
		
		//람다식 
//		User user = userRepository.findById(id).orElseThrow(()->{
//				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
//		});
//		return user;
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		
		//요청: 웹브라우저
		//user 객체  = 자바 오브젝트
		//변환 ( 웹브라우저가 이해할 수 있는 데이터 ) -> json
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 jackson라이브러리를 호출해서
		// user오브젝트를 json으로 변환해서 브라우저에 던져줌.
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
	}
}
