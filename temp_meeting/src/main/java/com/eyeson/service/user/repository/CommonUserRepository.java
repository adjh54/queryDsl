package com.eyeson.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.UserVO;

@Repository
public interface CommonUserRepository extends JpaRepository<UserVO, Long>{

}
