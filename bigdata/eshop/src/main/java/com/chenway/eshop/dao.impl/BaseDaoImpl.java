package com.chenway.eshop.dao.impl;

import com.chenway.eshop.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/*
BaseDao接口的实现类
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    private SessionFactory sf;//hibernate会话工厂，相当于连接池
    private Class<T> clazz;

    public BaseDaoImpl() {
        //取得子类的泛型化超类
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //得到第一个实际参数
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    //设置sf
    @Resource(name = "sf")
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public void saveEntity(T t) {
        sf.getCurrentSession().save(t);
    }

    public void updateEntity(T t) {
        sf.getCurrentSession().update(t);
    }

    public void saveOrUpdateEntity(T t) {
        sf.getCurrentSession().saveOrUpdate(t);
    }

    public void deleteEntity(T t) {
        sf.getCurrentSession().delete(t);
    }

    public T getEntity(Integer id) {
        return (T) sf.getCurrentSession().get(clazz, id);
    }

    /*
    按照HQL查询数据  Hibernate Query Language
     */
    public List<T> findByHQL(String hql, Object... objects) {
        Query query = sf.getCurrentSession().createQuery(hql);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        return query.list();
    }

    /*
    按照HQL进行批量写操作
     */
    public void execHQL(String hql, Object... objects) {
        Query query = sf.getCurrentSession().createQuery(hql);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        query.executeUpdate();
    }
}
