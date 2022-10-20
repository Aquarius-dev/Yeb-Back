package com.xxxx.yebserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yebserver.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Description: TODD
 * @Author aquarius
 * @Date 2022/10/13 09:10
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
    }

    @Test
    void should_find_all_departments() throws Exception {
        mockMvc.perform(get("/system/basic/department/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    void should_add_dep() throws Exception {
        department.setName("测试部门");
        department.setParentId(13);
        String jsonStr = objectMapper.writeValueAsString(department);
        mockMvc.perform(post("/system/basic/department/")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")).
                contentType(MediaType.APPLICATION_JSON).
                content(jsonStr))
                .andExpect(status().isOk()).andDo(print());
    }
}