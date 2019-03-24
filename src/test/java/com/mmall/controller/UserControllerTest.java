package com.mmall.controller;

import com.mmall.BaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-28 14:14
 **/
public class UserControllerTest extends BaseTest {

    @Test
    public void testLogin() throws Exception {

        String username = "ckx";
        String password = "ckx";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username", username)
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }
}
