package com.eyeson.service.user.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;

import com.eyeson.service.user.repository.CustomUserRepository;
import com.eyeson.service.vo.User;

@NoRepositoryBean
public class UserRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRepository {

	//QuerydslRepositorySupport 클래스에는 기본생성자가 없음.
    public UserRepositoryImpl() {
        super(User.class);
    }
    
    
    public void djf() {
    }
}
