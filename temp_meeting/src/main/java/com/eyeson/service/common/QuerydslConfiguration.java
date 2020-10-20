package com.eyeson.service.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * JPAQueryFactory를 주입 받아 Querydsl을 사용할 수 있게 됩니다.
 * @author JONGHOON
 *
 */
@Configuration
public class QuerydslConfiguration {

	@PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
