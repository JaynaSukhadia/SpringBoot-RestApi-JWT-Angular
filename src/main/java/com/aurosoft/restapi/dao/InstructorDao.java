package com.aurosoft.restapi.dao;

import com.aurosoft.restapi.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorDao extends JpaRepository<Instructor, Long> {

    @Query(value = "select i from  Instructor as i where i.fname like %:name% or i.lname like %:name%")
    Page<Instructor> findInstructorsByName(@Param("name") String name, PageRequest pageRequest);

    @Query(value = "select i from  Instructor as i where i.user.email = :email")
    Page<Instructor> findInstructorsByEmail(@Param("email") String email);
}
