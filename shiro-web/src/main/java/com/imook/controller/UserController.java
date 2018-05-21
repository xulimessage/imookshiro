package com.imook.controller;

import com.imook.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping(value = "/subLogin.do",method = RequestMethod.GET)
    @ResponseBody
    public String subLogin(User user){
        System.out.println("zzzzzzzzzzzzzzzzzz");


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());


        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        return "登陆成功";
    }
}
