package com.aitho.Service;

import com.aitho.Models.Course;
import com.aitho.Models.Teacher;
import com.aitho.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher _teacher = teacherRepository.save(new Teacher(teacher.getName(),teacher.getSurname(),teacher.getEmail()));
            return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
