package com.mz.aclasser.repository;

import com.mz.aclasser.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo,String> {

}
