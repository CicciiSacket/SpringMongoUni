package com.aitho.Controller;

import com.aitho.Models.Students;
import com.aitho.Models.Teacher;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllteachersTestOk() throws Exception {
        this.mockMvc.perform(get("/teacher")
                .header("email","pippo")
                .header("role","Student")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyJ9.iaOz2RfS9C_lIvjgAOmyfDMlHx3lyFsj5WPZTx83Ft35VyBfoHixLXdFjI0VgfnaTe-IWaoiuDDo2SVYaSbDdA"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllteachersTestForbidden() throws Exception {
        this.mockMvc.perform(get("/teacher")
                .header("email","pippo")
                .header("role","Student")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAllteachersTest4xx() throws Exception {
        this.mockMvc.perform(get("/teacher"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addTeacherTestOk() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","mail@mail","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(post("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(trueResult)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addTeacherTestBadRequest() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(post("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(trueResult)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addTeacherTestNotAcceptable() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","mail@mail","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(post("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(trueResult)))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void addTeacherTestForbidden() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","mail@mail","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(post("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(trueResult)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateTeacherMailTestOk() throws Exception {
        this.mockMvc.perform(put("/teacher/{id}","60e70f3ef89fb96eba35cbb1")
                .header("email","mario@").header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON)
                .content("NUOVAM"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateTeacherMailTestForbidden() throws Exception {
        this.mockMvc.perform(put("/teacher/{id}","60e70f3ef89fb96eba35cbb1")
                .header("email","mario@").header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON)
                .content("NUOVA"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateTeacherMailTestNotAcceptable() throws Exception {
        this.mockMvc.perform(put("/teacher/{id}","60e70f3ef89fb96eba35cbb1")
                .header("email","mario@").header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON)
                .content("NUOVAMAIL"))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void updateTeacherMailTestBadRequest() throws Exception {
        this.mockMvc.perform(put("/teacher/{id}","60e70f3ef89fb96eba35cbb1")
                .header("email","mario@").header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteTeacherTestOk() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","NUOVAMAIL","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(delete("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTeacherTestBadRequest() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(delete("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteTeacherTestForbidden() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","m.p@gmail.com","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(delete("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


    @Test
    public void deleteTeacherTestNotFound() throws Exception {
        Teacher testTeacher = new Teacher("Alfredo","Gallo","nonesiste","password","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testTeacher);

        this.mockMvc.perform(delete("/teacher")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTeacherFromListIDTest() throws Exception {
        this.mockMvc.perform(get("/teacher/search")
                .header("id","60e2cf979c1e853d3f958a5a")
                .header("id","60e2cf99c1e853d3f958a5a")
                .header("id","60e2cf9c1e853d3f958a5a"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

