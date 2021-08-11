package com.ithan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class ParamController {

    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username + ", password = " + password);
        return "success";
     }

    @RequestMapping("/testController")
    public String testServletAPI(
            //@RequestParam("user_name") String username, 此时没有该user_name就报错
            @RequestParam(value = "user_name", required = false, defaultValue = "hehe") String username,
            String password,
            String[] hobby){
        System.out.println("username = " + username + ", password = " + password + ", hobby = " + Arrays.toString(hobby));
        return "success";
    }
}
