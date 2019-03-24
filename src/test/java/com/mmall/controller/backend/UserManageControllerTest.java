package com.mmall.controller.backend;

import com.mmall.BaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserManageControllerTest extends BaseTest {

    @Test
    public void login() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/manage/user/login")
                .param("username", "ckx")
                .param("password", "ckx")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }
}