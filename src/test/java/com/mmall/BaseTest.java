package com.mmall;

import com.mmall.common.Const;
import com.mmall.model.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-29 11:03
 **/
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的需要用到的配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
// 用来声明加载的ApplicationContext是一个WebApplicationContext
@WebAppConfiguration
public class BaseTest {

    public MockMvc mockMvc;

    public MockHttpSession session;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = new MockHttpSession();

        User user = new User();
        user.setRole(1);
        session.setAttribute(Const.CURRENT_USER, user);
    }
}
