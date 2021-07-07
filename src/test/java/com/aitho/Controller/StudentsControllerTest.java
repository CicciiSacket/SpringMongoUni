package com.aitho.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class StudentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

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


}
