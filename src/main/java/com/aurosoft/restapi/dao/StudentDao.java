package com.aurosoft.restapi.dao;

import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentDao extends JpaRepository<Student , Long > {

    @Query(value = "select s from  Student as s where s.fname like %:name% or s.lname like %:name%")
    Page<Student> findStudentsByName(@Param("name") String name, PageRequest pageRequest);


    @Query(value = "select s from  Student as s where s.user.email = :email")
    Student findStudentByEmail(@Param("email") String email);
}
