package com.eyeson.service.user.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.LoginVO;
import com.eyeson.service.vo.QLoginVO;
import com.eyeson.service.vo.QUserVO;
import com.eyeson.service.vo.UserVO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CustomUserRepository extends QuerydslRepositorySupport {

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
    
    
    /**
     * 로그인 수행 시 '게스트 사용자' 정보 조회
     * @param inviteUuid
     * @return
     * @throws Exception
     */
    public LoginVO selectGuestInfo(String inviteUuid) throws Exception{
    	QLoginVO login = QLoginVO.loginVO;
    	
    	LoginVO selectGuestInfo = new LoginVO();
    	
    	
    	selectGuestInfo = from(login)
    					.select(Projections.bean(LoginVO.class
    							, login.email
    							, login.userName
    							, login.inviteUuid)
    							)
    					.where(login.inviteUuid.eq(inviteUuid))
    					.fetchOne();
    	
    	return selectGuestInfo;
    }


}
