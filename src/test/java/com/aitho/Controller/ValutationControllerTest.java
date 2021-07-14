package com.aitho.Controller;

import com.aitho.Models.Valutation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ValutationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    Valutation valutation = new Valutation("60e870d43281d272fbc6c4d2","60e6ca48414a6f445ca91fb8","60e2cf979c1e853d3f958a5a",29);

    @Test
    public void getAllValutationsTestOk() throws Exception {
        this.mockMvc.perform(get("/valutations")
                .header("email","mario@")
                .header("role","Admin")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJpb0AifQ.oA34y9uyA6UThvMx7aQiH6dqsFW9lVSq-U7PgHM7P1_FUT67YX7rFFktzUyN61_VXfHUuHgLbphndm9P5Ve_PA"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllValutationsTestForbidden() throws Exception {
        this.mockMvc.perform(get("/valutations")
                .header("email","mario@")
                .header("role","Admin")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getValutationsOfStudentTestOk() throws Exception {
        this.mockMvc.perform(get("/valutations/student")
                .header("email","pippo")
                .header("role","Student")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaXBwbyJ9.iaOz2RfS9C_lIvjgAOmyfDMlHx3lyFsj5WPZTx83Ft35VyBfoHixLXdFjI0VgfnaTe-IWaoiuDDo2SVYaSbDdA"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getValutationsOfStudentTestForbidden() throws Exception {
        this.mockMvc.perform(get("/valutations/student")
                .header("email","pippo")
                .header("role","Student")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getValutationOfTeacherOk() throws Exception {
        this.mockMvc.perform(get("/valutations/teacher")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getValutationOfTeacherForbidden() throws Exception {
        this.mockMvc.perform(get("/valutations/teacher")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void getValutationOfCourseOk() throws Exception {
        this.mockMvc.perform(get("/valutations/course/{id}","60e870d43281d272fbc6c4d2")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getValutationOfCourseForbidden() throws Exception {
        this.mockMvc.perform(get("/valutations/course/{id}","60e870d43281d272fbc6c4d2")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token",""))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void addValutationTestCreated() throws Exception {
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(valutation);

        this.mockMvc.perform(post("/valutations")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addValutationTestNotAcceptable() throws Exception {
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(valutation);

        this.mockMvc.perform(post("/valutations")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void addValutationTestNotFoundCourse() throws Exception {
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(valutation);

        this.mockMvc.perform(post("/valutations")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void addValutationTestNotFoundTeacher() throws Exception {
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(valutation);

        this.mockMvc.perform(post("/valutations")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void addValutationTestNotFoundStudent() throws Exception {
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(valutation);

        this.mockMvc.perform(post("/valutations")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtLnBAZ21haWwuY29tIn0.jZIfrfERlQ7PToru0BKECmSETGzEcJ6D3GM-sXcX1qhQLbPpman9u4irr5aSgF75meTMpOkvZ4-kYIjYEycBsw")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void addValutationTestForbidden() throws Exception {
        ObjectMapper resultTest = new ObjectMapper();
        String trueResult = resultTest.writeValueAsString(valutation);

        this.mockMvc.perform(post("/valutations")
                .header("email","m.p@gmail.com")
                .header("role","Teacher")
                .header("token","")
                .contentType(MediaType.APPLICATION_JSON).content(trueResult))
                .andDo(print())
                .andExpect(status().isForbidden());
    }



 }
