package com.aitho.Service;

import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.aitho.Models.TeacherRes;
import com.aitho.Repository.TeacherRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Boolean existTeacherById(String id) {return teacherRepository.existsById(id);}

    public Optional<Teacher> searchTeacherByEmail(@PathVariable("email") String email) {
        return teacherRepository.findTeacherByEmail(email);
    }

    public ResponseEntity<Teacher> findTeacherById(String id){
        return teacherRepository.findById(id).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public void addTeacher(@RequestBody Teacher teacher) {
        teacher.setToken(JWT.create().withSubject(teacher.getEmail()).sign(Algorithm.HMAC512(teacher.getPassword())));
        teacherRepository.save(new Teacher(teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getPassword(), teacher.getToken()));
    }

    public void updateTeachertMail(@PathVariable("id") String id, @RequestBody String newMail) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            Teacher _teacher = teacher.get();
            _teacher.setEmail(newMail);
            teacherRepository.save(_teacher);
        }
    }

    public void deleteTeacher(@RequestBody Teacher teacher) {
        Teacher _teacher = teacherRepository.findTeacherByEmail(teacher.getEmail()).get();
        teacherRepository.delete(_teacher);
    }

    public Map<String, TeacherRes> getTeachersFromIdList (@RequestHeader List<String> teachersID ) {
        return teacherRepository.findAll().stream().filter(teacher -> teachersID.contains(teacher.getId()))
                .map(n -> new TeacherRes(n.getId(), n.getName(), n.getSurname()))
                .collect(Collectors.toMap(TeacherRes::getId, value -> value));
    }



}
