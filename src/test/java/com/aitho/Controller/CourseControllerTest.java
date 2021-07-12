package com.aitho.Controller;

import com.aitho.Models.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllCoursesTestOkStudent() throws Exception {
        this.mockMvc.perform(get("/course")
                .header("email","pippo")
                .header("role","Student")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyJ9.iaOz2RfS9C_lIvjgAOmyfDMlHx3lyFsj5WPZTx83Ft35VyBfoHixLXdFjI0VgfnaTe-IWaoiuDDo2SVYaSbDdA"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCoursesTestOkAdmin() throws Exception {
        this.mockMvc.perform(get("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCoursesTestForbiddenStudent() throws Exception {
        this.mockMvc.perform(get("/course")
                .header("email","pippo")
                .header("role","Student")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAllCoursesTestForbiddenAdmin() throws Exception {
        this.mockMvc.perform(get("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void searchCourseTestOkStudents() throws Exception {
        this.mockMvc.perform(get("/course/{id}", "60e869c6618c60a4050fe61a")
                .header("email","pippo")
                .header("role","Student")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyJ9.iaOz2RfS9C_lIvjgAOmyfDMlHx3lyFsj5WPZTx83Ft35VyBfoHixLXdFjI0VgfnaTe-IWaoiuDDo2SVYaSbDdA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void searchCourseTestOkAdmin() throws Exception {
        this.mockMvc.perform(get("/course/{id}","60e869c6618c60a4050fe61a")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void searchCourseTestNotFound() throws Exception {
        this.mockMvc.perform(get("/course{id}", "nn")
                .header("email","pippo")
                .header("role","Student")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyJ9.iaOz2RfS9C_lIvjgAOmyfDMlHx3lyFsj5WPZTx83Ft35VyBfoHixLXdFjI0VgfnaTe-IWaoiuDDo2SVYaSbDdA"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchCourseTestForbidden() throws Exception {
        this.mockMvc.perform(get("/course/{id}","60e869c6618c60a4050fe61a")
                .header("email","pippo")
                .header("role","Student")
                .header("token"," ")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void addCourseTestBadRequest() throws Exception {
        Course testCourse = new Course("",16);
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCourseTestOk() throws Exception {
        Course testCourse = new Course("Informatica",16);
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addCourseTestNotAcceptable() throws Exception {
        Course testCourse = new Course("Informatica",16);
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void addCourseTestForbidden() throws Exception {
        Course testCourse = new Course("Informatica",16);
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateCourseCFUTestBadRequest() throws Exception {
        this.mockMvc.perform(put("/course/{id}","60e869c6618c60a4050fe61a")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(0)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCourseCFUTestOkAdmin() throws Exception {
        this.mockMvc.perform(put("/course/{id}","60e869c6618c60a4050fe61a")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(13)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateCourseCFUTestOkTeacher() throws Exception {
        this.mockMvc.perform(put("/course/{id}","60e869c6618c60a4050fe61a")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(16)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateCourseCFUTestForbidden() throws Exception {
        this.mockMvc.perform(put("/course/{id}","60e869c6618c60a4050fe61a")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(16)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    //delete

    @Test
    public void getTeachersCourseTestBadRequest() throws Exception {
        this.mockMvc.perform(get("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTeachersCourseTestNotFound() throws Exception {
        this.mockMvc.perform(get("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content("noName"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTeachersCourseTestOk() throws Exception {
        this.mockMvc.perform(get("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content("Informatica"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTeachersCourseTestForbidden() throws Exception {
        this.mockMvc.perform(get("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content("Informatica"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
