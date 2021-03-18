package com.mz.aclasser.service.impl;

import com.mz.aclasser.model.User;
import com.mz.aclasser.repository.UserRepository;
import com.mz.aclasser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public User LoginCheck(String username, String password) {
//        mongoTemplate.
        List<User> users = userRepository.findUserByUsername(username);
        if (users.size() > 0) {
            User user = users.get(0);
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User RegisterCheck(String username,String password) {
        List<User> users = userRepository.findUserByUsername(username);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setInfo(" ");
            mongoTemplate.save(user);
            return null;
        }
    }
}
