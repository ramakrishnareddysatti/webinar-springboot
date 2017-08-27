package com.here.springdata.mongodb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.here.springdata.mongodb.document.Address;
import com.here.springdata.mongodb.document.Employee;
import com.here.springdata.mongodb.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by nakkalwa on 25/08/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper objectMapper;

    private List<Employee> mockEmployeeList;

    @Before
    public void setUp(){
        mockEmployeeList = new ArrayList<>();
        mockEmployeeList.add(new Employee("1","John","Engineer",new Address("India","Mumbai")));
    }

    @Test
    public void testGetEmployees() throws Exception{
       Mockito.when(employeeRepository.findAll()).thenReturn(mockEmployeeList);
       RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/all").accept(MediaType.APPLICATION_JSON);
       MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       String expectedResult =  "[{\"id\":\"1\",\"name\":\"John\",\"designation\":\"Engineer\",\"address\":{\"country\":\"India\",\"city\":\"Mumbai\"}}]";
       JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(),false);
    }

    @Test
    public void testInsertNewEmployee() throws Exception{
        Employee employee = new Employee("2","David","Lead Engineer",new Address("United States","Chicago"));
        Mockito.when(employeeRepository.insert(Mockito.any(Employee.class))).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees/add")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expectedResult =  "2 is inserted successfully";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedResult, result.getResponse().getContentAsString());
    }
}
