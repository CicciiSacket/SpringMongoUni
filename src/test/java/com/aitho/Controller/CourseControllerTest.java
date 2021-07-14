package com.aitho.Controller;

import com.aitho.Models.Course;
import com.aitho.Models.RequestForCourse;
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
    private final String course = "Informatica";

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
        this.mockMvc.perform(get("/course/{id}", "60eda1e0a86cba020d0b45cf")
                .header("email","pippo")
                .header("role","Student")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyJ9.iaOz2RfS9C_lIvjgAOmyfDMlHx3lyFsj5WPZTx83Ft35VyBfoHixLXdFjI0VgfnaTe-IWaoiuDDo2SVYaSbDdA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void searchCourseTestOkAdmin() throws Exception {
        this.mockMvc.perform(get("/course/{id}","60eda1e0a86cba020d0b45cf")
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
        this.mockMvc.perform(get("/course/{id}","60eda1e0a86cba020d0b45cf")
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
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCourseTestBadRequest() throws Exception {
        Course testCourse = new Course("",16);
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(testCourse);

        this.mockMvc.perform(delete("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addCourseTestOk() throws Exception {
        Course testCourse = new Course("lillo",90);
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteCourseTestOk() throws Exception {
        this.mockMvc.perform(delete("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content("lillo"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void addCourseTestNotAcceptable() throws Exception {
        Course testCourse = new Course("Informatica",16);
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(testCourse);

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
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(testCourse);

        this.mockMvc.perform(post("/course")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteCourseTestForbidden() throws Exception {
        Course testCourse = new Course("Informatica",16);
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(testCourse);

        this.mockMvc.perform(delete("/course")
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
        this.mockMvc.perform(put("/course/{id}","60e870d43281d272fbc6c4d2")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(13)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateCourseCFUTestOkTeacher() throws Exception {
        this.mockMvc.perform(put("/course/{id}","60e870d43281d272fbc6c4d2")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(11)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateCourseCFUTestForbidden() throws Exception {
        this.mockMvc.perform(put("/course/{id}","60e870d43281d272fbc6c4d2")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(16)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getStudentsCourseTestBadRequest() throws Exception {
        this.mockMvc.perform(get("/course/student","")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getStudentsCourseTestNotFound() throws Exception {
        this.mockMvc.perform(get("/course/student","noName")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getStudentsCourseTestOk() throws Exception {
        this.mockMvc.perform(get("/course/student/{nameCourse}","Informatica")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentsCourseTestForbidden() throws Exception {
        this.mockMvc.perform(get("/course/student","Informatica")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getTeachersCourseTestForbidden() throws Exception {
        this.mockMvc.perform(get("/course/teacher/{nameCourse}","Informatica")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getTeachersCourseTestOk() throws Exception {
        this.mockMvc.perform(get("/course/teacher/{nameCourse}","Informatica")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTeachersCourseTestNotFound() throws Exception {
        this.mockMvc.perform(get("/course/student/{nameCourse}","noName")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTeachersCourseTestBadRequest() throws Exception {
        this.mockMvc.perform(get("/course/teacher","")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addStudentInCourseTestBadRequestNameCourse() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/student")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token", "")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void addStudentInCourseTestBadRequestMailStudent() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);
        this.mockMvc.perform(post("/course/students")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token", "")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void addStudentInCourseTestNotFoundCourse() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"pippo");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);
        this.mockMvc.perform(post("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void addStudentInCourseTestNotFoundStudents() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"nonesiste");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);
        this.mockMvc.perform(post("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void addStudentInCourseTestOk() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"pippo");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addStudentInCourseTestForbidden() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"pippo");
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteStudentInCourseTestOk() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"pippo");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void deleteStudentInCourseTestBadRequestCourse() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"pippo");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStudentInCourseTestBadRequestMail() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course,"");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/students")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void deleteStudentInCourseTestForbidden() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "pippo");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);
        this.mockMvc.perform(delete("/course/students")
            .header("email","mario@")
            .header("role","Admin")
            .header("token","")
            .contentType(MediaType.APPLICATION_JSON).content(trueResult))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

    @Test
    public void addTeacherInCourseTestOK() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse("Informatica", "m.p@gmail.com");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/teacher")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addTeacherInCourseTestForbidden() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "m.p@gmail.com");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void addTeacherInCourseTestBadRequest() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addTeacherInCourseTestNotFoundMail() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "nn");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(post("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTeacherInCourseTestNotFoundMail() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "nn");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTeacherInCourseTestBadRequest() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteTeacherInCourseTestForbidden() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "m.p@gmail.com");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteTeacherInCourseTestOK() throws Exception {
        RequestForCourse requestForCourse = new RequestForCourse(course, "m.p@gmail.com");
        ObjectMapper resultTestCourse = new ObjectMapper();
        String trueResult = resultTestCourse.writeValueAsString(requestForCourse);

        this.mockMvc.perform(delete("/course/teachers")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    public void getCoursesFromTeacherEmailTestOk() throws Exception {
        this.mockMvc.perform(get("/course/teachers/info")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .header("emailLogin", "m.p@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCoursesFromTeacherSurnameTestbadRequest() throws Exception {
        this.mockMvc.perform(get("/course/teachers/info")
                .header("email", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCoursesFromTeacherSurnameTestNotFound() throws Exception {
        this.mockMvc.perform(get("/course/teachers/info")
                .header("email", "mario@")
                .header("emailLogin", "mario@")
                .header("role", "Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCoursesFromTeacherSurnameTestForbidden() throws Exception {
        this.mockMvc.perform(get("/course/teachers/info")
                .header("email", "mario@")
                .header("emailLogin", "mario@")
                .header("role", "Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void searchFromListIDTest() throws Exception {
        this.mockMvc.perform(get("/course/search")
                .header("id","60e2cf979c1e853d3f958a5a")//0
                .header("id","60e870d43281d272fbc6c4d2")//1
                .header("id","60e2cf9c1e853d3f958a5a"))//0
                .andDo(print())
                .andExpect(status().isOk());
    }



}
