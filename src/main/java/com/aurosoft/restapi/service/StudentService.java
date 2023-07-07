package com.aurosoft.restapi.service;

import com.aurosoft.restapi.dto.InstructorDTO;
import com.aurosoft.restapi.dto.StudentDTO;
import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Student loadStudentById(Long studentId);

    Page<StudentDTO> loadStudentByName(String name, int page, int size);

    StudentDTO loadStudentByEmail(String email);

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    void removeStudent(Long studentId);
}
