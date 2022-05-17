package com.parshin.task_4.dao.mapper.impl;

import com.parshin.task_4.dao.mapper.Mapper;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.entity.UserAccessLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.parshin.task_4.command.AttributeName.*;

public class UserMapper implements Mapper<User> {
    @Override
    public Optional<User> mapEntity(ResultSet resultSet) {
        User user = new User();
        Optional<User> optionalUser;
        try {
            user.setLogin(resultSet.getString(LOGIN_ATTRIBUTE));
            user.setName(resultSet.getString(NAME_ATTRIBUTE));
            user.setSurname(resultSet.getString(SURNAME_ATTRIBUTE));
            //user.setBirthday(resultSet.getDate());
            user.setPhone(resultSet.getString(PHONE_ATTRIBUTE));
            user.setEmail(resultSet.getString(EMAIL_ATTRIBUTE));
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }
}
