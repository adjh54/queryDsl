package com.eyeson.service.user.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.QUser;
import com.eyeson.service.vo.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository 
public class UserQueryRepository extends QuerydslRepositorySupport{
	private final JPAQueryFactory queryFactory;

	 public UserQueryRepository(JPAQueryFactory queryFactory) {
		 	super(User.class);
	        this.queryFactory = queryFactory;
	}

	 
	 public User selectUserInfo(String email) {
		 QUser user	= QUser.user;
		 User result = (User) queryFactory
						.select(
							Projections.fields(User.class,
								  user.user_seq
								, user.email
								, user.user_name
								, user.user_pw
								, user.country
								, user.department
								, user.lang))
						.from(user)
						.where(user.email.eq(email))
						.where(user.enabled.eq("1"))
						.fetch();
			
			
			return result;
	 }
}
