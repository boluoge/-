package com.chenway.ssm.service.impl;

import com.chenway.ssm.dao.BaseDao;
import com.chenway.ssm.domain.Item;
import com.chenway.ssm.domain.Order;
import com.chenway.ssm.domain.User;
import com.chenway.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource(name = "itemDao")
    private BaseDao<Item> itemDao;

    @Resource(name = "userDao")
    public void setDao(BaseDao<User> dao) {
        super.setDao(dao);
    }

    /**
     * 长事务测试
     */
    public void longTx() {
        //插入item
        Item i = new Item();
        i.setItemName("ttt");

        Order o = new Order();
        o.setId(2);
        //
        itemDao.insert(i);

        this.delete(1);
    }

    public void save(User u) {
        if (u.getId() != null) {
            this.update(u);
        } else {
            this.insert(u);
        }
    }
}