package com.parshin.task_4.service.impl;

import com.parshin.task_4.dao.impl.UserDaoImpl;
import com.parshin.task_4.exception.DaoException;
import com.parshin.task_4.exception.ServiceException;
import com.parshin.task_4.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        //validate login, pass + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
