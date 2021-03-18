package com.mz.aclasser.controller;

import com.mz.aclasser.data.Recognition;
import com.mz.aclasser.data.RecognitionResult;
import com.mz.aclasser.model.Thumbnail;
import com.mz.aclasser.model.User;
import com.mz.aclasser.repository.ThumbnailRepository;
import com.mz.aclasser.service.ImageProcessingService;
import com.mz.aclasser.service.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import springfox.documentation.annotations.ApiIgnore;

@Log4j2
@Controller
public class AClasserController {

    @Autowired
    private ImageService imageService;

    private static final String IMAGE_ATTR = "IMAGE";

    private final ImageProcessingService imageProcessingService;

    public AClasserController(ImageProcessingService imageProcessingService) {
        this.imageProcessingService = imageProcessingService;
    }

//    @ApiIgnore
//    @RequestMapping(value = "/index")
//    public String index() {
//        return "index";
//    }
//
//    @ApiIgnore
//    @RequestMapping(value = "/services", method = RequestMethod.GET)
//    public String handleGetForm() {
//        return "services1";
//    }

    @ApiOperation("classify")
    @RequestMapping(value = "/classify", method = RequestMethod.POST)
    public String handleUploadForm(@RequestParam(value = "input-b2") MultipartFile file, Model model, HttpServletRequest httpServletRequest) {
        log.debug("request upload requested");
        RecognitionResult recognitionResult = imageProcessingService.processImageFile(file);
        List<Recognition> recognitions = recognitionResult.getRecognitions();
        BufferedImage imagePreview = recognitionResult.getImagePreview();

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String filename = imageService.storeLocal(file,user.getUsername() , imagePreview);
        httpServletRequest.getSession().setAttribute("thumNamme", filename);
        httpServletRequest.getSession().setAttribute(IMAGE_ATTR, imagePreview);

        log.debug("Found objects {}", recognitions);
        System.out.println(recognitions);
        if (recognitions.isEmpty()) {
            model.addAttribute("message", "No objects found");
        } else {
            model.addAttribute("recognitions", recognitions);
        }
        return "classify";
//        return "success";
    }

    @PostMapping(value = "/imageInfo")
    public String uploadImageInfo(@RequestParam(value = "albumname") String albumName ,HttpServletRequest httpServletRequest) {
        String tags1 = httpServletRequest.getParameter("tags");
        String tags2 = httpServletRequest.getParameter("tags1");
        List<String> tags11 = Arrays.asList(tags1.split("\\s*,\\s*"));
        List<String> tags22 =  Arrays.asList(tags2.split("\\s*,\\s*"));
        ArrayList<String> tags = new ArrayList<>(tags11);
        tags.addAll(new ArrayList<String>(tags22));
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String thumNamme = (String)httpServletRequest.getSession().getAttribute("thumNamme");
        System.out.println(tags);
        Boolean flag = imageService.saveThumInfo(thumNamme, user.getUsername(), user.getId(), albumName, tags);
        return "classify";
//        return flag.toString();
//        return "success";
    }

    @ApiIgnore
    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public void handleGetImg(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        BufferedImage image = (BufferedImage) httpServletRequest.getSession().getAttribute(IMAGE_ATTR);
        if (image != null) {
            httpServletResponse.setContentType("/image/png");
            ImageIO.write(image, "png", httpServletResponse.getOutputStream());
        } else {
            httpServletResponse.sendError(httpServletResponse.SC_NOT_FOUND);
        }
    }
}
