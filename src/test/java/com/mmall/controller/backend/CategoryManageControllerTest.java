package com.mmall.controller.backend;

import com.mmall.BaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryManageControllerTest extends BaseTest {

    @Test
    public void addCategory() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/manage/category/add_category")
                .param("categoryName", "test")
                .param("parentId", "1")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }

    @Test
    public void setCategoryName() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/manage/category/set_category_name")
                .param("categoryName", "test2")
                .param("categoryId", "100033")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }

    @Test
    public void getCategory() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/manage/category/get_category")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }

    @Test
    public void getCategoryAndDeepChildrenCategory() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/manage/category/get_deep_category")
                .param("categoryId", "0")
                .session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print());
    }
}