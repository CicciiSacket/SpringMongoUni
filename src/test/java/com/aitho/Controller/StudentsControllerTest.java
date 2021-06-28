package com.aitho.Controller;

import com.aitho.Models.Students;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class StudentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllStudentsTest() throws Exception {
        this.mockMvc.perform(get("/students"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSingleStudentsTest() throws Exception {
        this.mockMvc.perform(get("/students/{id}","60c21cfd9b3db31a7a02bcef"))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    public void addStudentTest() throws Exception {
//        var mario = new Students("mario","rossi","email");
//        mario.setId("marioooo");
//        ObjectMapper result = new ObjectMapper();
//        String trueResult = result.writeValueAsString(mario);
//        this.mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(trueResult))
//                .andDo(print())
//                .andExpect(status().isCreated());
//    }

//    //incompleto
//    @Test
//    public void updateStudentTest() throws Exception {
//        var mario = new Students("mario","rossi","email");
//        mario.setId("marioooo");
//        ObjectMapper result = new ObjectMapper();
//        String trueResult = result.writeValueAsString(mario);
//        this.mockMvc.perform(put("/students/{id}","60c21cfd9b3db31a7a02bcef").contentType(MediaType.APPLICATION_JSON).content(trueResult))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//    }




}
