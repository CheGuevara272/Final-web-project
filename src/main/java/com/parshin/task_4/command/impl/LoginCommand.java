package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.exception.CommandException;
import com.parshin.task_4.exception.ServiceException;
import com.parshin.task_4.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login);
                session.setAttribute("user_name", login);
                page = "pages/main.jsp";
            } else {
                request.setAttribute("login_msg", "Incorrect login or password");
                page = "index.jsp";
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
// 9:28