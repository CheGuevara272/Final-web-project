package com.parshin.task_4.dao;

import com.parshin.task_4.entity.AbstractEntity;
import com.parshin.task_4.exception.DaoException;

import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean delete(T t) throws DaoException;
    public abstract List<T> finaAll() throws DaoException;
    public abstract T update(T t) throws DaoException;
}
