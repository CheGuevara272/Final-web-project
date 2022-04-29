package com.parshin.task_4.dao;

import com.parshin.task_4.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
}
