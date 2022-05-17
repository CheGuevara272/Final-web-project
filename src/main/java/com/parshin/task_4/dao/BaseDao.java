package com.parshin.task_4.dao;

import com.parshin.task_4.entity.AbstractEntity;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean delete(T t) throws DaoException;
    public abstract boolean delete(int id) throws DaoException;
    public abstract Optional<T> findByParam(String paramName, String paramValue) throws DaoException;
    public abstract List<T> finaAll() throws DaoException;
    public abstract T update(T t) throws DaoException;
}
