package com.mz.aclasser.repository;

import com.mz.aclasser.model.Thumbnail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThumbnailRepository extends MongoRepository<Thumbnail,String> {
}
