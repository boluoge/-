package com.chenway.eshop.dao;

import java.util.List;

/*
Base接口  所有DAO接口都会继承它
 */
public interface BaseDao<T> {
    public void saveEntity(T t);

    public void updateEntity(T t);

    public void saveOrUpdateEntity(T t);

    public void deleteEntity(T t);

    public T getEntity(Integer id);

    /*
    按照HQL
     */
    public List<T> findByHQL(String hql, Object... objects);

    public void execHQL(String hql, Object... objects);
}
