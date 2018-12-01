package com.chenway.eshop.service;

import com.chenway.eshop.model.User;

/*
UserService业务接口
 */
public interface UserService extends BaseService<User> {
    public boolean isRegisted(String email);
}
