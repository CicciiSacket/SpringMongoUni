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
        Optional<Course> _course = courseRepository.findCourseById(id);
        return _course.orElse(null);
    }

    public List<Course> getCoursesFromIdList (@RequestHeader List<String> coursesID ) {
        return courseRepository.findAll().stream().filter(course -> coursesID.contains(course.getId())).collect(Collectors.toList());
    }

    public void addCourse(@RequestBody Course course) {
        courseRepository.save(course);
    }


    public void updateCourseCFU(@PathVariable("id") String id,@RequestBody Integer newCFU) {
        Optional<Course> _course = courseRepository.findById(id);
        if (_course.isPresent()) {
            Course course = _course.get();
            course.setCFU(newCFU);
            courseRepository.save(course);
        }
    }

    public void deleteCourse(@RequestBody Course course) {
        Course _course = courseRepository.findCourseByName(course.getName()).get();
        courseRepository.delete(_course);
    }

    public ArrayList<String> getStudentsCourse(@PathVariable("nameCourse") String nameCourse) {
        Optional<Course> course = courseRepository.findCourseByName(nameCourse);
        if (course.isPresent()){
            return course.get().getStudentsId();
        }
        return new ArrayList<String>();
    }

    public ArrayList<String> getTeachersCourse(@RequestBody String nameCourse) {
        Optional<Course> course = courseRepository.findCourseByName(nameCourse);
        if (course.isPresent()){
            return course.get().getTeachersId();
        }
        return new ArrayList<String>();
    }

    public void addStudentInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Students> students = studentsRepository.findStudentByEmail(requestForCourse.getMail());
        Optional<Course> _course = courseRepository.findCourseByName(requestForCourse.getCourse().getName());
        if (_course.isPresent() && students.isPresent()) {
            Course course = _course.get();
            course.getStudentsId().add(students.get().getId());
            courseRepository.save(course);
        }
    }

    public void deleteStudentInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Students> students = studentsRepository.findStudentByEmail(requestForCourse.getMail());
        Optional<Course> _course = courseRepository.findCourseByName(requestForCourse.getCourse().getName());
        if (_course.isPresent() && students.isPresent()) {
            Course course = _course.get();
            course.getStudentsId().remove(students.get().getId());
            courseRepository.save(course);
        }
    }

    public void addTeacherInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(requestForCourse.getMail());
        Optional<Course> _course = courseRepository.findCourseByName(requestForCourse.getCourse().getName());
        if (_course.isPresent() && teacher.isPresent()) {
            Course course = _course.get();
            course.getTeachersId().add(teacher.get().getId());
            courseRepository.save(course);
        }
    }

    public void deleteTeacherInCourse(@RequestBody RequestForCourse requestForCourse) {
        Optional<Teacher> teacher = teacherRepository.findTeacherByEmail(requestForCourse.getMail());
        Optional<Course> _course = courseRepository.findCourseByName(requestForCourse.getCourse().getName());
        if (_course.isPresent() && teacher.isPresent()) {
            Course course = _course.get();
            course.getTeachersId().remove(teacher.get().getId());
            courseRepository.save(course);
        }
    }

    public ArrayList<Course> searchCoursesOfTeachers(@RequestBody String teacherSurname) {
        ArrayList<Course> apj = new ArrayList<>();
        Optional<Teacher> teacher = teacherRepository.findTeacherBySurname(teacherSurname);
        for (Course course : courseRepository.findAll()) {
            if (course.getTeachersId().contains(teacher.get().getId())){
                apj.add(course);
            }
        }
        return apj;
    }

}
