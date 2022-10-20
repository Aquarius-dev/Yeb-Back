package com.xxxx.yebserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yebserver.entity.Joblevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Description: TODD
 * @Author aquarius
 * @Date 2022/10/12 13:00
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class JoblevelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Joblevel joblevel;

    @BeforeEach
    public void setUp(){
        joblevel = new Joblevel();
        joblevel.setId(12);
    }

    @Test
    void getAllJoblevels() throws Exception {
        mockMvc.perform(get("/system/basic/joblevel/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    void addJoblevel() throws Exception {
        joblevel.setId(null);
        joblevel.setName("上天的程序员");
        joblevel.setTitleLevel("正高级");
        String jsonStr =  objectMapper.writeValueAsString(joblevel);
        mockMvc.perform(post("/system/basic/joblevel/").
                        accept(MediaType.parseMediaType("application/json;charset=UTF-8")).
                        contentType(MediaType.APPLICATION_JSON).
                        content(jsonStr)).
                        andExpect(status().isOk()).
                        andDo(print());
    }

    @Test
    @Transactional
    void updateJoblevel() throws Exception {
        joblevel.setTitleLevel("初级");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr =  objectMapper.writeValueAsString(joblevel);
        mockMvc.perform(put("/system/basic/joblevel/").
                        accept(MediaType.parseMediaType("application/json;charset=UTF-8")).
                        contentType(MediaType.APPLICATION_JSON).
                        content(jsonStr)).
                andExpect(status().isOk()).
                andDo(print());
    }

    @Test
    @Transactional
    void deleteJoblevel() throws Exception {
        mockMvc.perform(delete("/system/basic/joblevel/{id}",12).
                        accept(MediaType.parseMediaType("application/json;charset=UTF-8")).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andDo(print());
    }

    @Test
    @Transactional
    void deletaJoblevelByIds() throws Exception{
        Integer[] batchIds = {1,2,3,4};
        String jsonStr = objectMapper.writeValueAsString(batchIds);
        mockMvc.perform(delete("/system/basic/joblevel/").
                        accept(MediaType.parseMediaType("application/json;charset=UTF-8")).
                        contentType(MediaType.APPLICATION_JSON).
                        content(jsonStr)).
                andExpect(status().isOk()).
                andDo(print());
    }
}