package com.eyeson.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eyeson.service.vo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository{

}
