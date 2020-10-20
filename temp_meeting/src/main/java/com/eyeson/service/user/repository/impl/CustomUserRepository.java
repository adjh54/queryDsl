package com.eyeson.service.user.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.QUser;
import com.eyeson.service.vo.UserDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CustomUserRepository extends QuerydslRepositorySupport{

	 /**
     * QueryDSL을 이용하여 entity 전체를 가져오는 방법 말고, 
     * 조회 대상을 지정하여 원하는 값만 조회하는 것을 프로젝션이라고 합니다.
     *
     * 프로젝션이 여러개 일 경우 ' Tuble'을 사용해야 한다.
     * 프로퍼티 접근, 필드 직접 접근, 생성자 사용
     */
    
    /**
     * fetch		: 컬럼 조회 대상이 여러건일 경우. 컬렉션 반환 (return : List<Tuple>)
	 * fetchOne  	: 조회 대상이 1건일 경우(1건 이상일 경우 에러). generic에 지정한 타입으로 반환	(return: Tuple)
	 * fetchFirst 	: 조회 대상이 1건이든 1건 이상이든 무조건 1건만 반환. 내부에 보면 return limit(1).fetchOne() 으로 되어있음
	 * fetchCount 	: 개수 조회. long 타입 반환
	 * fetchResults : 조회한 리스트 + 전체 개수를 포함한 QueryResults 반환. count 쿼리가 추가로 실행된다.
     */
	
	/**
	 * Projections.bean - 컬럼명 없이 결과값만 받
	 */
	
	@PersistenceContext
	private EntityManager em;
	
	
	private final JPAQueryFactory queryFactory;
	 
	// QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public CustomUserRepository(JPAQueryFactory queryFactory) {
        super(UserDTO.class);
        this.queryFactory = queryFactory;
    }

    /**
	 * 사용자 목록 조회 및 페이징
	 * @return
	 * @throws Exception
	 */
	public List<UserDTO> selectAllUserList() throws Exception {
		QUser user	= QUser.user;
		
		List<UserDTO> selectAllUserList = null;

		selectAllUserList = queryFactory.selectFrom(user)
														.where(user.enabled.eq("1"))
														.orderBy(user.reg_date.desc())
														.offset(0)
														.limit(10)
														.fetch();
		return selectAllUserList;
	}
    
    /**
     * 사용자 등록   
     * @param userInfo
     * @return
     */
	@Transactional
    public int insertUserInfo(UserDTO userInfo) {
    	QUser user	= QUser.user;
    	
    	
    	return 0;
    	
//    	INSERT INTO tb_user_list 
//		    (email, 
//		 user_pw, 
// 		 user_name,
// 		 legacy_id,
// 		 country,
// 		 department,
// 		 lang,
// 		 reg_id,
// 		 mod_id) 
//VALUES      ( #{email}, 
//  		  #{userPw},
//  		  #{userName},
//  		  #{legacyId},
//  		  #{country},
//  		  #{department},
//  		  #{lang},
//  		  'SYSTEM',
//  		  'SYSTEM'); 
    	
    }
    
    
    /**
     * 사용자 수정 
     * @param userInfo
     * @return
     */
    @Transactional 
    public Long updateUserInfo(UserDTO userInfo) {
    	
    	QUser user	= QUser.user;
    	LocalDateTime nowDate = LocalDateTime.now();
    	
    	/*
    	 * TODO: 차후 null 처리 하는 부분 추가해야함 
    	 */
    	return queryFactory.update(user)
    					   .set(user.authority, userInfo.getAuthority())
    					   .set(user.country, userInfo.getCountry())
    					   .set(user.department, userInfo.getDepartment())
    					   .set(user.enabled, userInfo.getEnabled())
    					   .set(user.lang, userInfo.getLang())
    					   .set(user.user_name, userInfo.getUser_name())
    					   .set(user.user_pw, userInfo.getUser_pw())
    					   .set(user.mod_date, nowDate)						//현재시간 주입
    					   .where(user.user_seq.eq(userInfo.getUser_seq()))
    					   .execute();
    }
    
    /**
     * 로그인 수행 시 '일반 사용자' 정보 조회 
     * @param email
     * @return
     * @throws Exception
     */
    public UserDTO selectUserInfo(String email) throws Exception {
    	QUser user	= QUser.user;
		
    	UserDTO selectUserInfo = null;
    	
    	selectUserInfo = from(user)
					    .select(Projections.bean(UserDTO.class
					    		, user.user_seq.as("userSeq")
		    					, user.email.as("email")
		    					, user.user_name.as("userName")
		    					, user.user_pw.as("userPw")
		    					, user.country.as("country")
		    					, user.department.as("department")
		    					, user.lang.as("lang")))
					    .where(user.email.eq(email))
		    			.where(user.enabled.eq("1"))
		    			.orderBy(user.email.desc())
					   .fetchOne();
    	
    	return selectUserInfo;
	}
    
    
    
    public List<Tuple> selectUserList() throws Exception{
    	QUser user	= QUser.user;
    	
    	return from(user).select(user.user_name, user.department).fetch();
    	
//    	return selectUserList;
    }
    
    
	


}
