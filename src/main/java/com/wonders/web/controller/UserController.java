package com.wonders.web.controller;

import com.wonders.core.entity.User;
import com.wonders.core.frame.CommonResult;
import com.wonders.core.service.UserService;
import com.wonders.core.utils.Md5PasswordEncoder;
import com.wonders.core.utils.SecurityContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户Controller
 * Created by Donge on 2017/1/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping(value = {"/index","/modify"})
    public void index(Model model) {

    }

    /**
     * 保存用户
     *
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveChange")
    public CommonResult saveChange(String password) {
        User user = SecurityContextUtil.getCurrentUser();
        user.setPasswordLength(password.length());
        user.setPassword(md5PasswordEncoder.encodePassword(password, null));
        userService.updateByPrimaryKeySelective(user);
        return new CommonResult(true);
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validate")
    public CommonResult validate(String password) {
        CommonResult result = new CommonResult(false);
        User user = SecurityContextUtil.getCurrentUser();
        password = md5PasswordEncoder.encodePassword(password, null);
        if (password.equals(user.getPassword())) {
            result.setSuccess(true);
        }
        return result;
    }

}
