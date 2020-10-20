package com.eyeson.service.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eyeson.service.user.repository.CommonUserRepository;
import com.eyeson.service.user.repository.impl.CustomUserRepository;
import com.eyeson.service.vo.UserVO;
import com.eyeson.service.vo.UserVO;
import com.querydsl.core.Tuple;


@RestController
@RequestMapping(value ="/v2")
public class UserController {

	
	@Autowired
	private CommonUserRepository commonUserRepository;
	
	@Autowired
	private CustomUserRepository customUserRepository;
	
	
	
	/**
	 * 로그인 수행 시 '일반 사용자' 정보 조회 
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/selectUserInfo", method = RequestMethod.GET)
	public UserVO selectUserInfo(@Param("email") String email) {
		System.out.println("parameter  ["+email+"]");
		
		UserVO selectUserInfo = new UserVO();
		try {
			
			selectUserInfo = customUserRepository.selectUserInfo(email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return selectUserInfo;
	}
	
	
	/**
	 * ID 값으로 사용자 정보 조회
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/selectUserById", method = RequestMethod.GET)
	public UserVO selectUserById(@Param("email") String email) {
		System.out.println("parameter  ["+email+"]");
		
		UserVO selectUserById = new UserVO();
		
		try {
			
			selectUserById = customUserRepository.selectUserById(email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("result Value >> ["+selectUserById.toString()+"]");
		
		return selectUserById;
	}
	
	
	
	
	
	
	/**
	 * 사용자 전체 조회
	 **** CustomUserRepository 사용
	 * @return List<UserVO>
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserVO> userList() {
		
		List<UserVO> userList = new ArrayList<UserVO>();
		
		try {
			
			userList = customUserRepository.selectAllUserList();
			
			for (UserVO resultVal : userList) {
				System.out.println("모든 사용자 이메일 ["+ resultVal.getEmail() +"]"); 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	/**
	 * 사용자 등록
	 *** JpaRepository의 내장 함수 사용 
	 * @param UserVO
	 * @return void 
	 */
	@RequestMapping(value="/user", method= RequestMethod.POST)
	public void insertUser(UserVO user) {
		
		// Hard Coding Area => 차후 파라미터로 받아서 처리 예정 
		LocalDateTime nowDate = LocalDateTime.now();
		
		user.setEmail("adjh54@naver.com");
		user.setUserPw("1234");
		user.setAuthority("ROLE_USER");
		user.setEnabled("1");
		user.setUserName("종훈이");
		user.setLegacyId("FC00001001");
		user.setCountry("KR");
		user.setDepartment("CLAB");
		user.setLang("KR");
		user.setRegId("SYSTEM");
		user.setRegDate(nowDate);
		user.setModId("ADMIN");
		user.setMod_date(nowDate);
		
//		customUserRepository.insertUserInfo(user);		//
		commonUserRepository.save(user);		// JpaRepository의 내장 메소드 'save' 사용
	}
	
	/**
	 * 사용자 정보 수정
	 **** CustomUserRepository 사용
	 * @param UserVO
	 * @return void
	 */
	@RequestMapping(value="/user", method= RequestMethod.PUT)
	public void updateUser(UserVO user) {
		   
		/*
		 * TODO: 차후 변경 파라미터로 변경 예정 
		 */
		 user.setAuthority("ROLE_USER");
		 user.setCountry("KR");
		 user.setDepartment("CLAB");
		 user.setEnabled("1");
		 user.setLang("KR");
		 user.setUserName("종훈이2");
		 user.setUserPw("369369");
		 user.setUserSeq(Long.parseLong("1"));
		
		customUserRepository.updateUserInfo(user);		// 수정기능
		
		
	}
	
	/**
	 * 사용자 정보 삭제
	 *** JpaRepository의 내장 함수 사용 
	 * @param user
	 * @return void
	 */
	@RequestMapping(value="/user", method= RequestMethod.DELETE)
	public void deleteUserforKey(@Param("user_seq") Long userSeq) {
		
		commonUserRepository.deleteById(userSeq);				// JpaRepository의 내장 메소드 'deleteById' 사용
	}
	


	
	
	
}
