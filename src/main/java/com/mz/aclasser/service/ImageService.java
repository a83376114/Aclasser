package com.mz.aclasser.service;

import com.mz.aclasser.model.Album;
import com.mz.aclasser.model.Thumbnail;
import com.mz.aclasser.model.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface ImageService {
    String uploadImage(MultipartFile file, User user);

    void init();
    String storeLocal(MultipartFile file, String username, BufferedImage bufferedImage);

    //    void storeImageToDB(String fileName,User user);

    Boolean saveThumInfo(String name, String userName, String uId, String albumName, List<String> tags);

    List<Thumbnail> loadAllThumbnailsInAlbum(String username,String albumName);

    List<Thumbnail> loadAllThumbnailsInUser(String username);

    List<Album> loadAllAlbums(String username);
}
