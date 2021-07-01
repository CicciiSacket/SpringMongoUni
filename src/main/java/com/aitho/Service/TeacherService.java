package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() { return teacherRepository.findAll(); }

    public Boolean existTeacherById(String id) {return teacherRepository.existsById(id);}

    public Optional<Teacher> searchTeacherByEmail(@PathVariable("email") String email) {
        return teacherRepository.findTeacherByEmail(email);
    }

    public ResponseEntity<Teacher> getTeacher(@RequestBody Teacher teacher) {
        Optional<Teacher> _teacher = teacherRepository.findById(teacher.getId());
        if(_teacher.isPresent()){
            return new ResponseEntity<>(_teacher.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public Optional<Teacher> findTeacherById(String id){
        return teacherRepository.findById(id);
    }

//    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
//        try {
//            Teacher _teacher = teacherRepository.save(new Teacher(teacher.getName(),teacher.getSurname(),teacher.getEmail()));
//            return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
//        }
//        catch(Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") String id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherUpgrade = teacherRepository.findById(id);
        if (teacherUpgrade.isPresent()) {
            Teacher _teacherUpgrade = teacherUpgrade.get();
            _teacherUpgrade.setEmail(teacher.getEmail());
            return new ResponseEntity<>(teacherRepository.save(_teacherUpgrade),HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteTeacher(@RequestBody Teacher teacher) {
        Optional<Teacher> _teacher = teacherRepository.findById(teacher.getId());
        if (_teacher.isPresent()) {
            String _student = _teacher.get().getId();
            teacherRepository.deleteById(_student);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
