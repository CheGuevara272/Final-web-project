package com.parshin.task_4.dao;

import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> authenticate(String login, String password) throws DaoException;

    boolean create(User user, String hashPassword) throws DaoException;
}
