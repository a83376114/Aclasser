package com.mz.aclasser.repository;

import com.mz.aclasser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findUserByUsername(String username) ;
//    List<User>
}
