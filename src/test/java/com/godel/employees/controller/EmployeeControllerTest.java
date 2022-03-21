package com.godel.employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godel.employees.SimpleWebApplication;
import com.godel.employees.model.Employee;
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
    @Autowired
    private ObjectMapper objectMapper;

    private final String request = "{\"firstName\":\"test1\",\"lastName\":\"test1\",\"departmentId\":1,\"jobTitle\":\"test1\",\"gender\":\"MALE\",\"dateOfBirth\":\"2000-11-11\"}";
    private final String expectedResult = "{\"employeeId\":%s,\"firstName\":\"test1\",\"lastName\":\"test1\",\"departmentId\":1,\"jobTitle\":\"test1\",\"gender\":\"MALE\",\"dateOfBirth\":\"2000-11-11\"}";

    @Test
    void testPost_successful() throws Exception {

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        Employee receivedEmployee = objectMapper.readValue(response, Employee.class);
        Long receivedEmployeeId = receivedEmployee.getEmployeeId();

        assert response.equals(String.format(expectedResult, receivedEmployeeId));

        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/employee/%s", receivedEmployeeId)));

    }
}