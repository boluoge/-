package com.chenway.ssm.service.impl;

import com.chenway.ssm.dao.BaseDao;
import com.chenway.ssm.domain.Item;
import com.chenway.ssm.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Resource(name = "itemDao")
    public void setDao(BaseDao<Item> dao) {
        super.setDao(dao);
    }
}