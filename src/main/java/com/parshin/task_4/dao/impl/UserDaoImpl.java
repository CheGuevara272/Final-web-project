package com.parshin.task_4.dao.impl;

import com.parshin.task_4.dao.BaseDao;
import com.parshin.task_4.dao.UserDao;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.DaoException;
import com.parshin.task_4.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) {
        return false;
    } //TODO сделать функционал метода

    @Override
    public boolean delete(User user) {
        return false;
    } //TODO сделать функционал метода

    @Override
    public List finaAll() {
        return null;
    } //TODO сделать функционал метода

    @Override
    public User update(User user) {
        return null;
    } //TODO сделать функционал метода

    @Override
    public boolean authenticate(String login, String password) throws DaoException {

        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                match = password.equals(passFromDb);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }
}
