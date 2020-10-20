package com.eyeson.service.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eyeson.service.user.repository.CommonUserRepository;
import com.eyeson.service.user.repository.impl.CustomUserRepository;
import com.eyeson.service.vo.UserDTO;
import com.querydsl.core.Tuple;


@RestController
@RequestMapping(value ="/v2")
public class UserController {

	
	@Autowired
	private CommonUserRepository commonUserRepository;
	
	@Autowired
	private CustomUserRepository customUserRepository;
	
	/**
	 * 사용자 전체 조회
	 **** CustomUserRepository 사용
	 * @return List<UserDTO>
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserDTO> userList() {
		
		List<UserDTO> userList = new ArrayList<UserDTO>();
		
		try {
			
			userList = customUserRepository.selectAllUserList();
			
			for (UserDTO resultVal : userList) {
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
	 * @param UserDTO
	 * @return void 
	 */
	@RequestMapping(value="/user", method= RequestMethod.POST)
	public void insertUser(UserDTO user) {
		
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
		
//		customUserRepository.insertUserInfo(user);		//
		commonUserRepository.save(user);		// JpaRepository의 내장 메소드 'save' 사용
	}
	
	/**
	 * [완료]사용자 정보 수정
	 **** CustomUserRepository 사용
	 * @param UserDTO
	 * @return void
	 */
	@RequestMapping(value="/user", method= RequestMethod.PUT)
	public void updateUser(UserDTO user) {
		   
		/*
		 * TODO: 차후 변경 파라미터로 변경 예정 
		 */
		 user.setAuthority("ROLE_USER");
		 user.setCountry("KR");
		 user.setDepartment("CLAB");
		 user.setEnabled("1");
		 user.setLang("KR");
		 user.setUser_name("종훈이2");
		 user.setUser_pw("369369");
		 user.setUser_seq(Long.parseLong("1"));
		
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
	

//===================== 예제 영역 ======================================================================
	
	/**
	  사용자 유저 조회
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/selectUserInfo", method = RequestMethod.GET)
	public UserDTO selectUserInfo(@Param("email") String email) {
		System.out.println("parameter  ["+email+"]");
		
		UserDTO selectUserInfo = new UserDTO();
		try {
			selectUserInfo = customUserRepository.selectUserInfo(email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return selectUserInfo;
	}
	
	
	
	/**
	 * 사용자 유저 모두 조회
	 * @return
	 * 
	 */
	@RequestMapping(value="/selectUserList", method = RequestMethod.GET)
	public List<Tuple> findAllUser() {
		List<Tuple> selectUserList = null;
		try {
			selectUserList = customUserRepository.selectUserList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return selectUserList;
	}
	
}
