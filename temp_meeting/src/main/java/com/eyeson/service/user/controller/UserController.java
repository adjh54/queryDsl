package com.eyeson.service.user.controller;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eyeson.service.user.repository.UserRepository;
import com.eyeson.service.vo.User;


@RestController
@RequestMapping(value ="/v2")
public class UserController {

	
	@Autowired
	private UserRepository userRepository;
	
	private EntityManager em;
	
	/**
	 * 사용자 전체 조회
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Iterable<User> userList() {
		
		// TODO: 내장 'save' 사용
		Iterable<User> userList = userRepository.findAll();
		
//		for(User userListItem : userList) {
//			System.out.println("userList Item email >>> "+ userListItem.toString());
//		}
		
		return userList;
	}
	
	/**
	 * 사용자 등록
	 * @param User
	 */
	@RequestMapping(value="/user", method= RequestMethod.POST)
	public void insertUser(User user) {

		// Hard Coding Area => 차후 파라미터로 받아서 처리 예정 
		LocalDateTime nowDate = LocalDateTime.now();
		user.setEmail("adjh54@naver.com");
		user.setUser_pw("1234");
		user.setAuthority("ROLE_USER");
		user.setEnabled("1");
		user.setUser_name("종훈이");
		user.setLegacyId("FC00001001");
		user.setCountry("KR");
		user.setDepartment("CLAB");
		user.setLang("KR");
		user.setReg_id("SYSTEM");
		user.setReg_date(nowDate);
		user.setMod_id("ADMIN");
		user.setMod_date(nowDate);
		
		// TODO: 내장 'save' 사용
		userRepository.save(user);
	}
	
	/**
	 * 키값으로 데이터 삭제
	 * @param user
	 */
	@RequestMapping(value="/user", method= RequestMethod.DELETE)
	public void deleteUserforKey(@Param("userSeq") Long userSeq) {
		
		// TODO: 내장 'deleteById' 사용
		userRepository.deleteById(userSeq);
	}
	
	
	/**
	 * 사용자 정보 수정
	 */
	@RequestMapping(value="/user", method= RequestMethod.PUT)
	public void updateUser(User user) {
		
//		String userName = "종훈이222";
//		String userPassword = "1234555555";
//		Long userSeq = (long) 4;
//		
//		int result = userRepository.update(userName, userPassword, userSeq);
//		
//		return result;
		
	}

//===================== 예제 영역 ======================================================================
	
	
	@RequestMapping(value="/selectUserInfo", method = RequestMethod.GET)
	public void selectUserInfo(@Param("email") String email) {
		System.out.println("parameter  ["+email+"]");
		
		
//		tb_user_list selectUserInfo = new tb_user_list();
		
		
//		try {
//			selectUserInfo = userRepository.findByEmail("adjh54@naver.com");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("selectLoginUserInfo RESULT !!!"+ selectUserInfo.getUser_name());
	}
	
//	@RequestMapping(value="/selectLoginUserInfo", method = RequestMethod.GET)
//	public UserVO selectLoginUserInfo(@Param("email") String email) {
//		System.out.println("parameter  ["+email+"]");
//		
//		UserVO selectLoginUserInfo = new UserVO();
//		
//		try {
//			selectLoginUserInfo = userRepository.selectLoginUserInfo(email);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("selectLoginUserInfo START !!!"+ selectLoginUserInfo);
//		return selectLoginUserInfo;
//	}
//	
//	@RequestMapping(value="/selectUserById", method = RequestMethod.GET)
//	public UserVO selectUserById(@Param("email") String email) {
//		System.out.println("parameter  ["+email+"]");
//		
//		UserVO selectUserById = new UserVO();
//		
//		try {
//			selectUserById = userRepository.selectUserById(email);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("selectUserById START !!!"+ selectUserById);
//		return selectUserById;
//	}
	
}
