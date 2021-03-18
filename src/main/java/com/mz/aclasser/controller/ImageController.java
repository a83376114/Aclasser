package com.mz.aclasser.controller;

import com.mz.aclasser.model.Album;
import com.mz.aclasser.model.Thumbnail;
import com.mz.aclasser.model.User;
import com.mz.aclasser.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ImageController {

    @Value("${file.upload.path}")
    private String path;
    @Autowired
    private ImageService imageService;


    //all photos of user
    @GetMapping("/allPhotos")
    public String loadAllPhotos(HttpServletRequest httpServletRequest,Model model) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String username = user.getUsername();
        List<Thumbnail> thumbnails = imageService.loadAllThumbnailsInUser(username);
        model.addAttribute("thumbnails", thumbnails);
        return "photo";
    }

    //all photos of album
    @GetMapping("/photo")
    public String loadAllThums(HttpServletRequest httpServletRequest, Model model) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String username = user.getUsername();
        String albumName = httpServletRequest.getParameter("album");
        List<Thumbnail> photos = imageService.loadAllThumbnailsInAlbum(username, albumName);
        String single = albumName.substring(0, 1).toUpperCase() + albumName.substring(1);
        model.addAttribute("single",single);
        model.addAttribute("photos", photos);
        return "single";
    }

//    @GetMapping("/getAllAlbums")
//    public List<Album> loadAllAlbums(HttpServletRequest httpServletRequest) {
//        User user = (User) httpServletRequest.getSession().getAttribute("user");
//        String username = user.getUsername();
//        return imageService.loadAllAlbums(username);
//    }
//
    @GetMapping("/image")
    public Object view(String name, HttpServletResponse response) {
        String file = path + name;
        System.out.println(file);
        File source = new File(file);
        response.setContentType("");
        try {
            FileCopyUtils.copy(new FileInputStream(source), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
