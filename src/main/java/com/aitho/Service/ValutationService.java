package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Models.Valutation;
import com.aitho.Repository.ValutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public List<Valutation> getStudentValutations(String id) { return valutationRepository.findStudentValutations(id); }

    public List<Valutation> getTeacherValutations(String id){return valutationRepository.findTeacherValutations(id);}

    public List<Valutation> getCourseValutations(String id){return valutationRepository.findCourseValutations(id);}

    public void addValutation(@RequestBody Valutation valutation) {
        Optional<Course> course = Optional.ofNullable(courseService.findCourseById(valutation.getId_course()));
        Boolean teacher = teacherService.existTeacherById(valutation.getId_teacher());
        Boolean student = studentService.existStudentById(valutation.getId_student());
        if ( course.isPresent() && teacher && student){
            valutationRepository.save(valutation);
        }
    }

    public ResponseEntity<Valutation> updateValutation(@PathVariable("id") String id, @RequestBody Valutation valutation) {
        Optional<Valutation> valutationUpgrade = valutationRepository.findById(id);
        if (valutationUpgrade.isPresent()) {
            Valutation _valutationUpgrade = valutationUpgrade.get();
            _valutationUpgrade.setVote(valutation.getVote());
            return new ResponseEntity<>(valutationRepository.save(_valutationUpgrade),HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteValutation(@RequestBody Valutation valutation) {
        Optional<Valutation> _valutation = valutationRepository.findById( valutation.getId());
        if (_valutation.isPresent()) {
            String valutationId = _valutation.get().getId();
            valutationRepository.deleteById(valutationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
