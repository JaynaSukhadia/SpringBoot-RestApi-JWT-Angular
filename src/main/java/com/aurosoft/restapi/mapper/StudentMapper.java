package com.aurosoft.restapi.mapper;


import com.aurosoft.restapi.dto.InstructorDTO;
import com.aurosoft.restapi.dto.StudentDTO;
import com.aurosoft.restapi.entity.Instructor;
import com.aurosoft.restapi.entity.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentDTO fromStudent(Student student)
    {
        StudentDTO studentDTO= new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }

    public Student fromStudentDTO(StudentDTO studentDTO)
    {
        Student student= new Student();
        BeanUtils.copyProperties(studentDTO, student);
        return student;
    }

}
