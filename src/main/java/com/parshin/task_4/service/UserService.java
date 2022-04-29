package com.parshin.task_4.service;

import com.parshin.task_4.exception.DaoException;
import com.parshin.task_4.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
}
