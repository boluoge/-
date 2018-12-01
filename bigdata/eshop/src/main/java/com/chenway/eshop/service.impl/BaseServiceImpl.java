package com.chenway.eshop.service.impl;

import com.chenway.eshop.dao.BaseDao;
import com.chenway.eshop.service.BaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
/*
    service跟Dao打交道，在service声明dao对象，调用dao实现方法。
 */

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDao<T> baseDao;
    private Class clazz;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public BaseServiceImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) superclass.getActualTypeArguments()[0];
    }

    public void saveEntity(T t) {
        baseDao.saveEntity(t);
    }


    public void updateEntity(T t) {
        baseDao.updateEntity(t);
    }

    public void saveOrUpdateEntity(T t) {
        baseDao.saveOrUpdateEntity(t);
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public void deleteEntity(T t) {
        baseDao.deleteEntity(t);
    }

    public T getEntity(Integer id) {
        return baseDao.getEntity(id);
    }

    public List<T> findByHQL(String hql, Object... objects) {
        return baseDao.findByHQL(hql, objects);
    }

    public void execHQL(String hql, Object... objects) {
        baseDao.execHQL(hql, objects);
    }

    public List<T> findAllEntities() {
        String hql = "from " + clazz.getSimpleName();
        return this.findByHQL(hql);
    }
}
