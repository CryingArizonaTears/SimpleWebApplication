package com.godel.controller;

import com.godel.SimpleWebApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SimpleWebApplication.class)
@AutoConfigureMockMvc
@PropertySource("classpath:application.properties")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String request = "{\"firstName\":\"test1\",\"lastName\":\"test1\",\"departmentId\":1,\"jobTitle\":\"test1\",\"gender\":\"MALE\",\"dateOfBirth\":\"2000-11-11\"}";
    private final String expectedResult = "{\"employeeId\":%s,\"firstName\":\"test1\",\"lastName\":\"test1\",\"departmentId\":1,\"jobTitle\":\"test1\",\"gender\":\"MALE\",\"dateOfBirth\":\"2000-11-11\"}";

    @Test
    void testPost_successful() throws Exception {

        String responseId = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        String receivedJson = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/employee/%s", responseId))).andReturn().getResponse().getContentAsString();
        assert receivedJson.equals(String.format(expectedResult, responseId));

    }
}