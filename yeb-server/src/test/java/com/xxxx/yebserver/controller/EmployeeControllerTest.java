package com.xxxx.yebserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yebserver.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Description: 测试EmployeeController
 * @Author aquarius
 * @Date 2022/10/15 07:35
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    private MultiValueMap<String,String> params;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        params = new LinkedMultiValueMap<>();
    }

    @Test
    void should_find_employee_by_name_wang() throws Exception {
        params.add("name","王");
        mockMvc.perform(get("/employee/basic/").params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(is(7)))
                .andDo(print());
    }

    @Test
    void should_find_all_employee() throws Exception {
        mockMvc.perform(get("/employee/basic/"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(jsonPath("$.total").value(100))
                .andExpect(jsonPath("$.data.length()").value(10))
                .andDo(print());
    }

    @Test
    void should_find_by_politicId() throws Exception {
        params.add("politicId","1");
        mockMvc.perform(get("/employee/basic/").params(params))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(EmployeeController.class))
                .andExpect(jsonPath("$.data[*].name",hasItem("黄荣")))
                .andDo(print());
    }

    @Test
    void should_find_all_employee_pages() throws Exception {
        params.add("currentPage","2");
        mockMvc.perform(get("/employee/basic/").params(params))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data[*].id",hasItem(20)));
    }

}