package com.aurosoft.restapi.impl;

import com.aurosoft.restapi.dao.StudentDao;
import com.aurosoft.restapi.dto.StudentDTO;
import com.aurosoft.restapi.entity.Course;
import com.aurosoft.restapi.entity.Student;
import com.aurosoft.restapi.entity.User;
import com.aurosoft.restapi.mapper.StudentMapper;
import com.aurosoft.restapi.service.StudentService;
import com.aurosoft.restapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.stream.Collectors;


@Service
@Transactional

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private StudentMapper studentMapper;
    private UserService userService;

    public StudentServiceImpl(StudentDao studentDao, StudentMapper studentMapper, UserService userService) {
        this.studentDao = studentDao;
        this.studentMapper = studentMapper;
        this.userService = userService;
    }

    @Override
    public Student loadStudentById(Long studentId) {
        return studentDao.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Student with Id" +studentId+"Not Found" ));
    }

    @Override
    public Page<StudentDTO> loadStudentByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> studentsPage = studentDao.findStudentsByName(name,pageRequest);
        return  new PageImpl<>(studentsPage.getContent().stream().map(student -> studentMapper.fromStudent(student)).collect(Collectors.toList()),pageRequest,studentsPage.getTotalElements());

    }

    @Override
    public StudentDTO loadStudentByEmail(String email) {
        return studentMapper.fromStudent(studentDao.findStudentByEmail(email));
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        User user = userService.createUser(studentDTO.getUser().getEmail(),studentDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(),"Student");
        Student student=studentMapper.fromStudentDTO(studentDTO);
        student.setUser(user);
        Student savedStudent= studentDao.save(student);
        return studentMapper.fromStudent(savedStudent);


    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
       Student loadedStudent = loadStudentById(studentDTO.getStudentId());
       Student student = studentMapper.fromStudentDTO(studentDTO);
       student.setUser(loadedStudent.getUser());
       student.setCourses(loadedStudent.getCourses());
       Student updatedStudent = studentDao.save(student);
       return  studentMapper.fromStudent(updatedStudent);

    }

    @Override
    public void removeStudent(Long studentId) {
    Student student = loadStudentById(studentId);
        Iterator<Course> courseIterator = student.getCourses().iterator();
        if(courseIterator.hasNext())
        {
            Course course = courseIterator.next();
            course.removeStudentFromCourse(student);

        }
        studentDao.deleteById(studentId);
    }
}
