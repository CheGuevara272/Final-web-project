package com.parshin.task_4.service;

import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticateUser(String login, String password) throws ServiceException;
    boolean addUser(Map<String, String> userMap) throws ServiceException;
}
