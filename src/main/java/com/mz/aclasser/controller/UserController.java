package com.mz.aclasser.controller;

import com.mz.aclasser.model.User;
import com.mz.aclasser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/login_check", method = RequestMethod.POST)
    public User loginCheck(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,HttpServletRequest httpServletRequest) {
        User user = userService.LoginCheck(username, password);
        if (user != null) {
            httpServletRequest.getSession().setAttribute("user", user);
            return user;
        } else {
            return null;
        }
//        User user = userRepository.findUserByUsername(username);
////        System.out.println(password);
////        System.out.println(user);
////        if (user != null) {
////            if (password.equals(user.getPassword())) {
////                httpServletRequest.setAttribute("user", user);
////                return "index";
////            } else {
////                System.out.println("password wrong");
////                return "error";
////            }
////        } else {
////            System.out.println("user not exits");
////            return "error";
////        }
//        if (user != null) {
//            System.out.println(user);
//            if (password.equals(user.getPassword())) {
//                return user;
//            } else {
//                return null;
//            }
//        }
//        return null;
    }

    @RequestMapping(value = "/register_check", method = RequestMethod.POST)
    public User registerCheck(String username,String password) {
        User user1 = userService.RegisterCheck(username,password);
        return user1;
    }
}
