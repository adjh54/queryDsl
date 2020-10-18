package com.eyeson.service.user.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.eyeson.service.user.repository.CustomUserRepository;
import com.eyeson.service.vo.QUser;
import com.eyeson.service.vo.UserDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport{

	 private final JPAQueryFactory queryFactory;
	 
	// QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(UserDTO.class);
        this.queryFactory = queryFactory;
    }

    /**
     * QueryDSL을 이용하여 entity 전체를 가져오는 방법 말고, 
     * 조회 대상을 지정하여 원하는 값만 조회하는 것을 프로젝션이라고 합니다.
     *
     * 프로젝션이 여러개 일 경우 ' Tuble'을 사용해야 한다.
     * 프로퍼티 접근, 필드 직접 접근, 생성자 사용
     */
	public UserDTO selectUserInfo(String email) throws Exception {
		QUser user	= QUser.user;
		
		UserDTO result =  (UserDTO) queryFactory
						.select(
							Projections.bean(UserDTO.class,
								  user.user_seq.as("userSeq")
								, user.email.as("email")
								, user.user_name.as("userName")
								, user.user_pw.as("userPw")
								, user.country.as("country")
								, user.department.as("department")
								, user.lang).as("lang"))
						.from(user)
						.where(user.email.eq(email))
						.where(user.enabled.eq("1"))
						.fetch();
		
		return result;
	}




	@SuppressWarnings("unchecked")
	public List<UserDTO> findAll() throws Exception {
		QUser user	= QUser.user;
		
		List<UserDTO> resultList = new ArrayList<UserDTO>();
		
		resultList = (List<UserDTO>) queryFactory
				.from(user)
				.orderBy(user.reg_date.desc())
				.fetch();
		
		return resultList;
		
//		List<User> resultList = new ArrayList<User>();
//		resultList =  (List<User>) queryFactory.from(user).orderBy(user.reg_date.desc()).list(user);
	}
	
	
}
