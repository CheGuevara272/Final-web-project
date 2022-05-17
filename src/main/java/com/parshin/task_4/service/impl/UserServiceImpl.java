package com.parshin.task_4.service.impl;

import com.parshin.task_4.dao.impl.UserDaoImpl;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.DaoException;
import com.parshin.task_4.exception.ServiceException;
import com.parshin.task_4.service.UserService;
import com.parshin.task_4.util.PasswordEncoder;
import com.parshin.task_4.validator.AttributeValidator;
import com.parshin.task_4.validator.impl.AttributeValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Map;
import java.util.Optional;

import static com.parshin.task_4.command.AttributeName.*;

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticateUser(String login, String password) throws ServiceException {
        AttributeValidator attributeValidator = AttributeValidatorImpl.getInstance();
        if (!attributeValidator.validateLogin(login) || !attributeValidator.validatePassword(password)) {
            logger.log(Level.INFO, "user -{}- or password wasn't found", login);
            return Optional.empty();
        }
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        String hashPassword = PasswordEncoder.getHashedPassword(password);
        try {
            return userDao.authenticate(login, hashPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean addUser(Map<String, String> userMap) throws ServiceException {
        User user = new User();
        String hashPassword;

        user.setLogin(userMap.get(LOGIN_ATTRIBUTE));
        user.setName(userMap.get(NAME_ATTRIBUTE));
        user.setSurname(userMap.get(SURNAME_ATTRIBUTE));
        user.setBirthday(Date.valueOf(userMap.get(BIRTHDAY_ATTRIBUTE)));
        user.setPhone(userMap.get(PHONE_ATTRIBUTE));
        user.setEmail(userMap.get(EMAIL_ATTRIBUTE));

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        hashPassword = PasswordEncoder.getHashedPassword(userMap.get(PASSWORD_ATTRIBUTE));
        boolean result = false;
        try {
            result = userDao.create(user, hashPassword);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error in addUser in UserService. {}", e.getMessage());
        }
        return result;
    }


}
