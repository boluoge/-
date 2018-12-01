package com.chenway.ssm.service;

import com.chenway.ssm.domain.User;

import java.util.List;

/**
 *
 */
public interface UserService extends BaseService<User> {
    public void longTx();

    public void save(User u);

    public List<User> selectPage(int offset, int len);
}
