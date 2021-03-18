package com.mz.aclasser.controller;

import com.mz.aclasser.model.Album;
import com.mz.aclasser.model.User;
import com.mz.aclasser.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    ImageService imageService;

    @GetMapping(value = {"/","/index"})
    public String index(HttpServletRequest httpServletRequest, Model model) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            List<Album> albums = imageService.loadAllAlbums(user.getUsername());
            model.addAttribute("albums", albums);
            return "index";
        } else {
            return "login";
        }
    }

//    @GetMapping("/single")
//    public String single() {
//        return "single";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/classify")
    public String classify() {
        return "classify";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
