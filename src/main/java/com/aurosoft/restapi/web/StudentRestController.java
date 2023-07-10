package com.aurosoft.restapi.web;


import com.aurosoft.restapi.dto.CourseDTO;
import com.aurosoft.restapi.dto.StudentDTO;
import com.aurosoft.restapi.entity.User;
import com.aurosoft.restapi.service.CourseService;
import com.aurosoft.restapi.service.StudentService;
import com.aurosoft.restapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentRestController {

    private StudentService studentService;

    private UserService userService;
    private CourseService courseService;

    public StudentRestController(StudentService studentService, UserService userService, CourseService courseService) {
        this.studentService = studentService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping
    public Page<StudentDTO> searchStudents(@RequestParam(name = "keyword", defaultValue = "")String keyword,
                                           @RequestParam(name="page", defaultValue = "0")int page,
                                           @RequestParam(name = "size", defaultValue = "5")int size)
    {
        return studentService.loadStudentByName(keyword, page, size);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId)
    {
        studentService.removeStudent(studentId);
    }

    @PostMapping
    public  StudentDTO saveStudent(@RequestBody StudentDTO studentDTO)
    {
        User user = userService.loadUserByEmail(studentDTO.getUser().getEmail());
        if(user!=null) throw new RuntimeException("Email Exist");
        return  studentService.createStudent(studentDTO);
    }

    @PutMapping("/{studentId}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long studentId)
    {
        studentDTO.setStudentId(studentId);
        return studentService.updateStudent(studentDTO);
    }

    @GetMapping("/{studentId}/courses")
    public  Page<CourseDTO> corseByStudentId(@PathVariable Long studentId,
                                             @RequestParam (name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "5")int size)
    {
        return courseService.fetchCoursesForStudent(studentId,page,size);

    }

    @GetMapping("/{studentId}/other-courses")
    public Page<CourseDTO> nonSubscribedCoursesByStudentId(@PathVariable Long studentId,
                                                           @RequestParam (name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "5")int size)
    {
        return  courseService.fetchNonEnrolledInCoursesForStudent(studentId,page,size);

    }

    @GetMapping("/find")
    public StudentDTO loadStudentsByEmail(@RequestParam (name = "email", defaultValue = "")String  email)
    {
        return  studentService.loadStudentByEmail(email);

    }
}
