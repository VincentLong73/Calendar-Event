package com.hedspi.team45.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hedspi.team45.entity.User;

@Transactional
@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
