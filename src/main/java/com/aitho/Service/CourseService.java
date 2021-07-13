package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.RequestForCourse;
import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Repository.CourseRepository;
import com.aitho.Repository.StudentsRepository;
import com.aitho.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentsRepository studentsRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentsRepository studentsRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentsRepository = studentsRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> getAllCourses() { return courseRepository.findAll(); }

    public Course findCourseById(@PathVariable("id") String id) {
        return courseRepository.findCourseById(id).orElse(null);
    }

    public List<Course> getCoursesFromIdList (@RequestHeader List<String> coursesID ) {
        return courseRepository.findAll().stream().filter(course -> coursesID.contains(course.getId())).collect(Collectors.toList());
    }

    public void addCourse(@RequestBody Course course) {
        courseRepository.save(course);
    }


    public void updateCourseCFU(@PathVariable("id") String id, @RequestBody Integer newCFU) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            course.get().setCFU(newCFU);
            courseRepository.save(course.get());
        }
    }

    public void deleteCourse(@RequestBody String courseName) {
        courseRepository.findCourseByName(courseName).ifPresent(courseRepository::delete);
    }

    public ArrayList<String> getStudentsCourse(@PathVariable("nameCourse") String nameCourse) {
        Optional<Course> course = courseRepository.findCourseByName(nameCourse);
        if (course.isPresent()){
            return course.get().getStudentsId();
        }
        return new ArrayList<>();
    }

    public ArrayList<String> getTeachersCourse(@RequestBody String nameCourse) {
        Optional<Course> course = courseRepository.findCourseByName(nameCourse);
        if (course.isPresent()){
            return course.get().getTeachersId();
        }
        return new ArrayList<>();
    }

    public void addStudentInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Students> student = studentsRepository.findStudentByEmail(requestForCourse.getMail());
        Optional<Course> course = courseRepository.findCourseByName(requestForCourse.getCourseName());
        if (course.isPresent() && student.isPresent()) {
            course.get().getStudentsId().add(student.get().getId());
            courseRepository.save(course.get());
        }
    }

    public void deleteStudentInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Students> students = studentsRepository.findStudentByEmail(requestForCourse.getMail());
        Optional<Course> course = courseRepository.findCourseByName(requestForCourse.getCourseName());
        if (course.isPresent() && students.isPresent()) {
            course.get().getStudentsId().remove(students.get().getId());
            courseRepository.save(course.get());
        }
    }

    public void addTeacherInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(requestForCourse.getMail());
        Optional<Course> course = courseRepository.findCourseByName(requestForCourse.getCourseName());
        if (course.isPresent() && teacher.isPresent()) {
            course.get().getTeachersId().add(teacher.get().getId());
            courseRepository.save(  course.get());
        }
    }

    public void deleteTeacherInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(requestForCourse.getMail());
        Optional<Course> course = courseRepository.findCourseByName(requestForCourse.getCourseName());
        if (course.isPresent() && teacher.isPresent()) {
            course.get().getTeachersId().remove(teacher.get().getId());
            courseRepository.save(course.get());
        }
    }

    public List<Course> searchCoursesOfTeachers(@RequestHeader(value="queryEmail") String queryEmail) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(queryEmail);
        List<Course> teacherCourses = null;
        if(teacher.isPresent()){
            teacherCourses = courseRepository.findAll().stream().filter(course -> course.getTeachersId().contains(teacher.get().getId())).collect(Collectors.toList());
        }
        return teacherCourses;
    }

}
