package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.Students;
import com.aitho.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() { return courseRepository.findAll(); }

    public Boolean existCourseById(String id) {return courseRepository.existsById(id); }

    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        try {
            Course _course = courseRepository.save(course);
            return new ResponseEntity<>(_course, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Course> findCourseById(String id){
        return courseRepository.findById(id);
    }

    public ResponseEntity<Course> findCourseByName(@PathVariable("name") String name){
        Optional<Course> _course = courseRepository.findCourseByName(name);
        if(_course.isPresent()){
            return new ResponseEntity<>(_course.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Course> updateCourse(@PathVariable("id") String id,@RequestBody Course course) {
        Optional<Course> courseUpgrade = courseRepository.findById(id);
        if (courseUpgrade.isPresent()) {
            Course _courseUpgrade = courseUpgrade.get();
            _courseUpgrade.setCFU(course.getCFU());
            return new ResponseEntity<>(courseRepository.save(_courseUpgrade),HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteCourse(@RequestBody Course course) {
        Optional<Course> _course = courseRepository.findById( course.getId());
        if (_course.isPresent()) {
            String _student = _course.get().getId();
            courseRepository.deleteById(_student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
