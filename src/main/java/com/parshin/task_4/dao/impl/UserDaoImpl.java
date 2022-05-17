package com.parshin.task_4.dao.impl;

import com.parshin.task_4.dao.BaseDao;
import com.parshin.task_4.dao.UserDao;
import com.parshin.task_4.dao.mapper.impl.UserMapper;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.DaoException;
import com.parshin.task_4.pool.ConnectionPool;
import com.parshin.task_4.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    static Logger logger = LogManager.getLogger();
    private static final String SELECT_PASSWORD_BY_LOGIN = "SELECT password FROM library.users WHERE login = ?";

    private static final String INSERT_USER = """
            INSERT INTO library.users(login, hash_password, name, surname, email, birthday, phone) 
            VALUES(?,?,?,?,?,?,?)""";

    private static final String SELECT_USER_BY_PARAMETER = "SELECT * FROM library.users WHERE %s = ?";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    } //TODO сделать функционал метода

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }

    @Override
    public Optional<User> findByParam(String paramName, String paramValue) throws DaoException {
        ProxyConnection proxyConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            preparedStatement = proxyConnection.prepareStatement(String.format(SELECT_USER_BY_PARAMETER, paramName));
            preparedStatement.setString(1, paramValue);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalUser = new UserMapper().mapEntity(resultSet);
                logger.log(Level.INFO, "User was found by param {} and param value {}", paramName, paramValue);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in findUserByOneParam");
            throw new DaoException(e);
        } finally {
            whileClosing(proxyConnection, preparedStatement, resultSet);
        }
        return optionalUser;
    }

    @Override
    public List finaAll() {
        return null;
    } //TODO сделать функционал метода

    @Override
    public User update(User user) {
        return null;
    } //TODO сделать функционал метода

    @Override
    public Optional<User> authenticate(String login, String password) throws DaoException { //TODO Переделать
        Optional<User> optionalUser = Optional.empty();
        ProxyConnection proxyConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            preparedStatement = proxyConnection.prepareStatement(SELECT_PASSWORD_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalUser = new UserMapper().mapEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Error in authenticate method");
            throw new DaoException(e);
        } finally {
            whileClosing(proxyConnection, preparedStatement, resultSet);
        }
        return optionalUser;
    }

    @Override
    public boolean create(User user, String hashPassword) throws DaoException {
        ProxyConnection proxyConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            proxyConnection = ConnectionPool.getInstance().getConnection();
            preparedStatement = proxyConnection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, hashPassword);
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDate(6, user.getBirthday());
            preparedStatement.setString(7, user.getPhone());
        } catch (SQLException e) {
            logger.log(Level.ERROR, "UserDao error in create method. {}", e.getMessage());
            throw new DaoException("UserDao error in create method", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (proxyConnection != null) {
                    proxyConnection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Dao error while closing resources. {}", e.getMessage());
            }
        }
        return true;
    }

    private void whileClosing(ProxyConnection proxyConnection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (proxyConnection != null) {
                proxyConnection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Dao error while closing resources. {}", e.getMessage());
        }
    }
}
