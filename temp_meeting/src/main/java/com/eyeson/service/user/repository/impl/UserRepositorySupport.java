package com.eyeson.service.user.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.eyeson.service.user.repository.CustomUserRepository;
import com.eyeson.service.vo.QUser;
import com.eyeson.service.vo.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport implements CustomUserRepository {

	 private final JPAQueryFactory queryFactory;
	 
	// QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

	@Override
	public User findByEmail(String email) throws Exception {
		QUser user	= QUser.user;
		return (User) queryFactory
				.select(Projections.fields(user.user_seq
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
	}
}
