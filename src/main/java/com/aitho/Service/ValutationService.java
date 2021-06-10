package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.Valutation;
import com.aitho.Repository.ValutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ValutationService {

    private final ValutationRepository valutationRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public ValutationService(ValutationRepository valutationRepository, CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.valutationRepository = valutationRepository;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public List<Valutation> getAllValutations() { return valutationRepository.findAll(); }

    public ResponseEntity<Valutation> addValutation(@RequestBody Valutation valutation) {
        try{
            Course course = courseService.findCourseById(valutation.getId_course()).get();
            teacherService.findTeacherById(valutation.getId_teacher()).get();
            //Placeholder studente
            if (valutation.getCFU() <= course.getmaxCFU() && valutation.getCFU() >= course.getMinCFU() ){
                return new ResponseEntity<>(valutation, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e ){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
