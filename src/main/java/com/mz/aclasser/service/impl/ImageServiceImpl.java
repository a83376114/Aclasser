package com.mz.aclasser.service.impl;

import com.mz.aclasser.model.Album;
import com.mz.aclasser.model.Thumbnail;
import com.mz.aclasser.model.User;
import com.mz.aclasser.repository.AlbumRepository;
import com.mz.aclasser.repository.ThumbnailRepository;
import com.mz.aclasser.repository.UserRepository;
import com.mz.aclasser.service.ImageService;
import com.mz.aclasser.storage.StorageException;
import com.mz.aclasser.storage.StorageProperties;
import com.mz.aclasser.util.TimeString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {

    private final Path rootLocation;
    private final Path thumLocation;

    @Autowired
    ThumbnailRepository thumbnailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    MongoTemplate mongoTemplate;


    public ImageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.thumLocation = Paths.get(properties.getThumLocation());
    }

    @Override
    public String uploadImage(MultipartFile file, User user) {
        return null;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String storeLocal(MultipartFile file, String username, BufferedImage bufferedImage) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                return null;
            }
            String timeString = new TimeString().getTimeString();
            String fileName = username + "_" + timeString + originalFilename;
            //Store Original image
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, this.rootLocation.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            //Store Thumbnail image
            String thumFileName = this.thumLocation + "/" + fileName;
            File thumFile = new File(thumFileName);
            ImageIO.write(bufferedImage, "jpeg", thumFile);

//            Thumbnail thumbnail = new Thumbnail();
//            thumbnail.settName(fileName);
//            thumbnail.setUsername(user.getUsername());
//            thumbnail.setuId(user.getId());
//            thumbnailRepository.save(thumbnail);
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file" + originalFilename, e);
        }
    }


    @Override
    public Boolean saveThumInfo(String name, String userName, String uId, String albumName, List<String> tags) {
        try {
            Thumbnail thumbnail = new Thumbnail();
            thumbnail.setuId(uId);
            thumbnail.setUsername(userName);
            thumbnail.settName(name);
            thumbnail.setaName(albumName);
            thumbnail.setTags(tags);
            thumbnailRepository.save(thumbnail);

//            Query uQuery = Query.query(Criteria.where("username").is(userName));
//            Optional<S> a_name = albumRepository.findOne(Query.query(Criteria.where("a_name").is(albumName)),Album.class,"album");
            Query query = Query.query(Criteria.where("a_name").is(albumName));
            Album album1 = mongoTemplate.findOne(query, Album.class, "album");
            if (album1 != null) {
                mongoTemplate.findAndModify(query, Update.update("cover", name), Album.class, "album");
            } else {
                Album album = new Album();
                album.setaName(albumName);
                album.setCover(name);
                album.setUsername(userName);
                albumRepository.save(album);
                System.out.println(thumbnail.getId() + "   " + thumbnail.getaName());
            }
//            userRepository.
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Thumbnail> loadAllThumbnailsInAlbum(String username, String albumName) {
        Query query = Query.query(Criteria.where("username").is(username).andOperator(Criteria.where("a_name").is(albumName)));
        List<Thumbnail> thumbnails = mongoTemplate.find(query, Thumbnail.class);
        System.out.println(thumbnails.get(0).gettName());
        return thumbnails;
    }

    @Override
    public List<Thumbnail> loadAllThumbnailsInUser(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        List<Thumbnail> thumbnails = mongoTemplate.find(query, Thumbnail.class);
        return thumbnails;

    }

//    private List<Thumbnail> loadThumbs(Query query) {
//        List<Thumbnail> thumbnails = mongoTemplate.find(query, Thumbnail.class);
//        ArrayList<String> thumbs = new ArrayList<>();
//        for (Thumbnail thumbnail : thumbnails) {
//            if (thumbnail.gettName() != null && !"".equals(thumbnail.gettName())) {
//                thumbs.add(thumbnail.gettName());
//            }
//        }
//        return thumbs.toArray(new String[thumbs.size()]);
//    }

    @Override
    public List<Album> loadAllAlbums(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        List<Album> albums = mongoTemplate.find(query, Album.class);
//        ArrayList<String> albums1 = new ArrayList<>();
//        for (Album album : albums) {
//            if (album.getaName() != null && !"".equals(album.getaName())) {
//                albums1.add(album.getaName());
//            }
//        }
        return albums;
    }
}


//    @Override
//    public void storeImageToDB(String fileName, User user) {
//        Thumbnail thumbnail = new Thumbnail();
//        thumbnail.settName(fileName);
//        thumbnail.setUsername(user.getUsername());
//        thumbnail.setuId(user.getId());
//        thumbnailRepository.save(thumbnail);
//    }



