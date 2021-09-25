package com.liang.controller;


import com.liang.pojo.Admin;
import com.liang.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    //所有的界面层，会有业务层的对象
    @Resource
    AdminService adminService;

    //实现登陆判断，并进行相应的跳转
    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){
        Admin admin= adminService.login(name,pwd);
        //System.out.println("========1");
        if (admin!=null){
            //登录成功
            request.setAttribute("admin",admin);
            return "main";
        }else {
            //登录失败
            request.setAttribute("errmsg","用户名或密码不正确");
            return "login";
        }

    }
}
