package com.aurosoft.restapi.dao;

import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
