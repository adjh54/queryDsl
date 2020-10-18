package com.eyeson.service.user.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.eyeson.service.vo.UserDTO;
import com.querydsl.core.Tuple;

public interface CustomUserRepository {

	
	public abstract List<UserDTO> findAll() throws Exception;
	
	
	public abstract UserDTO findByEmail(@Param("email") String email) throws Exception;

	/*
	 * ** 정보 사항
	 *	위치기반 , 이름 기반 쿼리가 있는데 
	 * nativeQuery = true 해당 옵션을 주면 '위치기반' 쿼리를 수행한다.
	 * ** 필수 사항!!!
	 * - @Query 함수를 해당 Repository에서 사용할 경우 nativeQuery=true 옵션을 주어야 한다.
	 * 
	 * ** 문법 설명!!!
	 * CASE1 [ = ?(value)] 		: 고정 파라미터 값을 넣을 경우 사용 
	 * CASE2 [column = : param] 	: 파라미터로 값을 바 경우 
	 * - 해당 경우 param에 대해서 @Param으로 파라미터를 정의 해 주어야 한다.
	 * CASE3 [ count(column) :: param ] 
	 */

	
//	@Query(value= "SELECT a.user_seq, a.email, a.user_name, a.user_pw, a.country, a.department, a.lang FROM tb_user_list a WHERE a.email = :email AND a.enabled ='1'", nativeQuery = true)
	
	
	// 현재 잘되는 상태 
//	@Query(value= "SELECT m FROM tb_user_list m WHERE m.email = :email AND m.enabled ='1' ")
//	@Query(value= "SELECT m.user_seq, m.user_name FROM tb_user_list m WHERE m.email = :email AND m.enabled ='1' ")
//	public abstract UserVO findByEmail(@Param("email") String email) throws Exception;

//	Optional<User> findByName(String name);
	
	
//	@Query(value= "SELECT a.user_seq, a.email, a.user_name, a.country, a.department, a.lang FROM tb_user_list a WHERE a.email=:email AND a.enabled = '1'", nativeQuery = true)
//	public abstract UserVO selectLoginUserInfo(@Param("email") String email) throws Exception;
//
//	@Query(value= "SELECT a.email as ID, a.user_pw as PASSWORD, a.authority as AUTHORITY, a.enabled as ENABLED, a.user_name AS NAME, a.lang as LANG FROM tb_user_list a WHERE a.email=:email AND a.enabled = '1'", nativeQuery=true)
//	public abstract UserVO selectUserById(@Param("email") String email) throws Exception;
}