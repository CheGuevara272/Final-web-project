package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.command.UserAttributeName;
import com.parshin.task_4.dao.impl.UserDaoImpl;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.entity.UserAccessLevel;
import com.parshin.task_4.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        //int userId = TODO сделать генератор id
        String login = request.getParameter(UserAttributeName.LOGIN_ATTRIBUTE);
        String password = request.getParameter(UserAttributeName.PASSWORD_ATTRIBUTE);
        UserAccessLevel accessLevel = UserAccessLevel.valueOf(request.getParameter(UserAttributeName.ACCESS_LEVEL_ATTRIBUTE));
        String name = request.getParameter(UserAttributeName.NAME_ATTRIBUTE);
        String surname = request.getParameter(UserAttributeName.SURNAME_ATTRIBUTE);
        String birthday = request.getParameter(UserAttributeName.BIRTHDAY_ATTRIBUTE);
        String phone = request.getParameter(UserAttributeName.PHONE_ATTRIBUTE);
        String email = request.getParameter(UserAttributeName.EMAIL_ATTRIBUTE);
        //TODO Как лучше сделать валидацию пораметров? Проверку на повторы и т.д.

        User user = new User(login, password, accessLevel, name, surname, birthday, phone, email); //TODO сделать через сервисы
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        String page;
        boolean userAdded = userDao.insert(user);
        Router router = new Router();
        if (userAdded) {
            router.setCurrentPage(PagePath.INDEX_PAGE_PATH);
        } else {
            router.setCurrentPage(PagePath.REGISTER_PAGE_PATH);
        }
        return router;
    }
}
