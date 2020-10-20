package com.eyeson.service.user.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.QUserVO;
import com.eyeson.service.vo.UserVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CustomUserRepository extends QuerydslRepositorySupport {

    /**
     * 결과값 리턴
     * fetch		: 컬럼 조회 대상이 여러건일 경우. 컬렉션 반환 (return : List<Tuple>)
	 * fetchOne  	: 조회 대상이 1건일 경우(1건 이상일 경우 에러). generic에 지정한 타입으로 반환	(return: Tuple)
	 * fetchFirst 	: 조회 대상이 1건이든 1건 이상이든 무조건 1건만 반환. 내부에 보면 return limit(1).fetchOne() 으로 되어있음
	 * fetchCount 	: 개수 조회. long 타입 반환
	 * fetchResults : 조회한 리스트 + 전체 개수를 포함한 QueryResults 반환. count 쿼리가 추가로 실행된다.
     */
	
	/**
	 * Projections.bean - 컬럼명 없이 결과값만 받
	 */
	
	private final JPAQueryFactory queryFactory;
	 
	// QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public CustomUserRepository(JPAQueryFactory queryFactory) {
        super(UserVO.class);
        this.queryFactory = queryFactory;
    }

    /**
	 * 사용자 목록 조회 및 페이징
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectAllUserList() throws Exception {
		QUserVO user	= QUserVO.userVO;
		
		List<UserVO> selectAllUserList = null;

		selectAllUserList = queryFactory.selectFrom(user)
														.where(user.enabled.eq("1"))
														.orderBy(user.regDate.desc())
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
    public int insertUserInfo(UserVO userInfo) {
		QUserVO user	= QUserVO.userVO;
    	
    	
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
    public Long updateUserInfo(UserVO userInfo) {
    	
    	QUserVO user	= QUserVO.userVO;
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
    					   .set(user.userName, userInfo.getUserName())
    					   .set(user.userPw, userInfo.getUserPw())
    					   .set(user.mod_date, nowDate)						//현재시간 주입
    					   .where(user.userSeq.eq(userInfo.getUserSeq()))
    					   .execute();
    }
    
    /**
     * 로그인 수행 시 '일반 사용자' 정보 조회 
     * @param email
     * @return
     * @throws Exception
     */
    public UserVO selectUserInfo(String email) throws Exception {
    	QUserVO user	= QUserVO.userVO;
		
    	UserVO selectUserInfo = new UserVO();
    	
    	selectUserInfo = from(user)
						.select(Projections.bean(UserVO.class
								, user.userSeq.as("userSeq")
								, user.email.as("email")
								, user.userName.as("userName")
								, user.userPw.as("userPw")
								, user.country.as("country")
								, user.department.as("department")
								, user.lang.as("lang"))
								)
						.where(user.email.eq(email))
						.where(user.enabled.eq("1"))
						.orderBy(user.email.desc())
					    .fetchOne();
    	
    	return selectUserInfo;
	}
    

	/**
	 * ID 값으로 사용자 정보 조회
	 * @param email
	 * @return
	 */
    public UserVO selectUserById(String email) throws Exception {
    	QUserVO user = QUserVO.userVO;
    	
    	UserVO selectUserById = new UserVO();
    	
    	
    	selectUserById = from(user)
    					.select(Projections.bean(UserVO.class
    							, user.email.as("ID")
    							, user.userPw.as("PASSWORD")
    							, user.authority.as("AUTHORITY")
    							, user.enabled.as("ENABLED")
    							, user.userName.as("NAME")
    							, user.lang.as("LANG"))
    							)
						.where(user.email.eq(email))
						.where(user.enabled.eq("1"))
    					.fetchOne();
    			
    	return selectUserById;
    }


}
