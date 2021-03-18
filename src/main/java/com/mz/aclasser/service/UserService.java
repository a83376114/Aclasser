package com.mz.aclasser.service;

import com.mz.aclasser.model.User;

public interface UserService {
    User LoginCheck(String username, String password);

    User RegisterCheck(String username, String password);
}
