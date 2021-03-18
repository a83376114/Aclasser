package com.mz.aclasser.repository;

import com.mz.aclasser.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
