package com.aurosoft.restapi.dao;

import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentDao extends JpaRepository<Student , Long > {

    @Query(value = "select s from  Student as s where s.fname like %:name% or s.lname like %:name%")
    List<Instructor> findStudentsByName(@Param("name") String name);


    @Query(value = "select s from  Student as s where s.user.email = :email")
    List<Instructor> findStudentByEmail(@Param("email") String email);
}
