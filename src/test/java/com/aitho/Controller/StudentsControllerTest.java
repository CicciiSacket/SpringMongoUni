package com.aitho.Controller;

import com.aitho.Models.Students;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class StudentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String email = "12";
    private String emailRemove = "";

    @Test
    public void getAllStudentsTestOk() throws Exception {
        this.mockMvc.perform(get("/student").header("email","m.p@gmail.com").header("role","Teacher").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void getAllStudentsTest4xx() throws Exception {
        this.mockMvc.perform(get("/student"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getAllStudentsTestForbidden() throws Exception {
        this.mockMvc.perform(get("/student").header("email","m.p@gmail.com").header("role","Teacher").header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void searchStudentTestOK() throws Exception {
        this.mockMvc.perform(get("/student/{id}","60e4469f2754065bfabf44e6").header("email","m.p@gmail.com").header("role","Teacher").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void searchStudentTestForbidden() throws Exception {
        this.mockMvc.perform(get("/student/{id}","60e4469f2754065bfabf44e6").header("email","m.p@gmail.com").header("role","Teacher").header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void searchStudentTestNotFound() throws Exception {
        this.mockMvc.perform(get("/student/{id}","nn").header("email","m.p@gmail.com").header("role","Teacher").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchStudentTest4xx() throws Exception {
        this.mockMvc.perform(get("/student/{id}","60e4469f2754065bfabf44e6"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void addStudentTestCreated() throws Exception {
        var testStudent = new Students("pippo","pluto","12","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(post("/student").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addStudentTestForbidden() throws Exception {
        var testStudent = new Students("pippo","pluto","pippo","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(post("/student").header("email","mario@").header("role","Admin").header("token"," ").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void addStudentTestNotAcceptable() throws Exception {
        var testStudent = new Students("pippo","pluto","pippo","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(post("/student").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void addStudentTestBadRequest() throws Exception {
        var testStudent = new Students("pippo","pluto","","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(post("/student").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStudentTestOk() throws Exception {
        var testStudent = new Students("pippo","pluto","12","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(delete("/student").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentTestBadRequest() throws Exception {
        var testStudent = new Students("pippo","pluto","","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(delete("/student").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStudentTestNotFound() throws Exception {
        var testStudent = new Students("matteo","annaro","mattei","mattei","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(delete("/student").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStudentTestForbidden() throws Exception {
        var testStudent = new Students("pippo","pluto","12","mario","");
        ObjectMapper resultTestStudent = new ObjectMapper();
        String trueResult = resultTestStudent.writeValueAsString(testStudent);
        this.mockMvc.perform(delete("/student").header("email","mario@").header("role","Admin").header("token","").contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateStudentMailOk() throws Exception {
        this.mockMvc.perform(put("/student/{id}","60e6ca48414a6f445ca91fb8").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content("NUOVAMAIL"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudentMailBadRequest() throws Exception {
        this.mockMvc.perform(put("/student/{id}","60e6ca48414a6f445ca91fb8").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateStudentMailNotAcceptable() throws Exception {
        this.mockMvc.perform(put("/student/{id}","60e6ca48414a6f445ca91fb8").header("email","mario@").header("role","Admin").header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA").contentType(MediaType.APPLICATION_JSON).content("NUOVAMAIL"))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void updateStudentMailForbidden() throws Exception {
        this.mockMvc.perform(put("/student/{id}","60e6ca48414a6f445ca91fb8").header("email","mario@").header("role","Admin").header("token","").contentType(MediaType.APPLICATION_JSON).content("lill"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


}
