package com.gh.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpController {
		
		private static final String TAG = "HttpControllerTest : ";
		
		@GetMapping("/http/lombok")
		public String lombokTest() {
			Member m = Member.builder()
												.userName("gh")
												.password("123123")
												.email("sjkh1231@gmail.com")
												.id(1)
												.build(); 
			System.out.println(TAG+"getter : "+m.getUserName());
			m.setUserName("yk");
			System.out.println(TAG+"setter : "+m.getUserName());
			return "lombok 테스트 완료";
		}
		
		@GetMapping("/http/get")
		//@RequestParam으로 단일 건으로 받을 수도 있지만, MessageConverter가  Member 클래스로 매핑시켜 준다.
		public String getTest(Member m) {
			
			return "get 요청:" + m.getId() + " , UserName : " + m.getUserName();
		}
		
		@PostMapping("/http/post")
		public String postTest(@RequestBody Member m) {
			return "post요청" + m.getId() + " , UserName : " + m.getUserName() +", Email : " + m.getEmail();
		}
		
		@PutMapping("/http/put")
		public String putTest() {
			return "put요청";
		}
		
		@DeleteMapping("/http/delete")
		public String deleteTest() {
			return "delete요청";
		}
}
