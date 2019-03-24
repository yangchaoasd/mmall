package com.mmall.controller;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.model.User;
import com.mmall.service.UserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: chenkaixin
 * @createTime: 2018-12-27 18:43
 **/
@Api(value = "/user", description = "用户接口")
@RestController
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping("/login")
    public ServerResponse login(HttpSession session,
                                HttpServletResponse httpServletResponse,
                                @RequestParam String username,
                                @RequestParam String password) {

        ServerResponse response = userService.login(username, password);
        if (response.isSuccess()) {
            CookieUtil.wirteLoginToken(httpServletResponse, session.getId());
            RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return response;
    }

    @ApiOperation(value = "用户登出")
    @RequestMapping("/logout")
    public ServerResponse logout(HttpServletRequest request, HttpServletResponse response) {

        String loginToken = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request, response);
        RedisShardedPoolUtil.del(loginToken);

//        session.removeAttribute(Const.CURRENT_USER);

        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "创建用户")
    @RequestMapping("/register")
    public ServerResponse<String> register(User user) {

        return userService.register(user);
    }

    @ApiOperation(value = "判断用户名或者密码是否已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "str", value = "值", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "email或者username", required = true, dataType = "String")
    })
    @RequestMapping("/check_valid")
    public ServerResponse<String> checkValid(@RequestParam String str, @RequestParam String type) {

        return userService.checkValid(str, type);
    }

    @ApiOperation(value = "登录状态获得用户信息")
    @RequestMapping("/get_user_info")
    public ServerResponse<User> getUserInfo(HttpServletRequest request) {

//        User user = (User) session.getAttribute(Const.CURRENT_USER);

        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录，无法获取用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取用户的信息");
    }

    @ApiOperation(value = "获得找回密码的问题")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String")
    @RequestMapping("/forget_get_question")
    public ServerResponse<String> forgetGetQuestion(String username) {

        return userService.selectQuestion(username);
    }

    @ApiOperation(value = "校验问题答案")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "question", value = "问题", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "answer", value = "答案", required = true, dataType = "String")
    })
    @RequestMapping("/forget_check_answer")
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {

        return userService.checkAnswer(username, question, answer);
    }

    @ApiOperation(value = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "passwordNew", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "forgetToken", value = "token", required = true, dataType = "String")
    })
    @RequestMapping("/forget_reset_password")
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {

        return userService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    @ApiOperation(value = "登录状态重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "passwordOld", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "passwordNew", value = "新密码", required = true, dataType = "String"),
    })
    @RequestMapping("/reset_password")
    public ServerResponse<String> resetPassword(HttpServletRequest request, String passwordOld, String passwordNew) {

        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录，无法获取用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);

        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }

    @ApiOperation(value = "登录状态更新用户信息")
    @RequestMapping("/update_information")
    public ServerResponse<User> updateInformation(HttpServletRequest request, User user) {

        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录，无法获取用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr, User.class);

        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            RedisShardedPoolUtil.setEx(loginToken, JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return response;
    }

    @ApiOperation(value = "登录状态获得用户信息")
    @RequestMapping("/get_information")
    public ServerResponse<User> getInformation(HttpServletRequest request) {

        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录，无法获取用户的信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr, User.class);

        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要登录");
        }
        return userService.getInformation(currentUser.getId());
    }
}
