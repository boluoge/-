package com.chenway.eshop.service.impl;


import com.chenway.eshop.dao.BaseDao;
import com.chenway.eshop.model.User;
import com.chenway.eshop.service.UserService;
import com.chenway.eshop.util.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
UserService具体实现类
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    /**
     * 重新该方法，需要注入指定的UserDao对象
     */
    @Resource(name = "userDao")
    public void setDao(BaseDao<User> dao) {
        super.setBaseDao(dao);
    }

    public boolean isRegisted(String email) {
        String hql = "from User u where u.email=?";
        List<User> list = this.findByHQL(hql, email);
        return ValidateUtil.isVaild(list);

    }
}
