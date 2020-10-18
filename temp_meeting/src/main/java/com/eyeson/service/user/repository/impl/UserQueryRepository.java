package com.eyeson.service.user.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.UserDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository 
public class UserQueryRepository {
//    private final JPAQueryFactory queryFactory;
//
//    public List<UserDTO> findByName(String name) {
//        return queryFactory.selectFrom(user)
//                .where(academy.name.eq(name))
//                .fetch();
//    }
}