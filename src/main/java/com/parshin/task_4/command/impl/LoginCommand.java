package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.exception.CommandException;
import com.parshin.task_4.exception.ServiceException;
import com.parshin.task_4.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserServiceImpl userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login);
                session.setAttribute("user_name", login);
                router.setRedirectType();
                router.setCurrentPage(PagePath.MAIN_PAGE_PATH);
            } else {
                request.setAttribute("login_msg", "Incorrect login or password");
                router.setCurrentPage(PagePath.INDEX_PAGE_PATH);
            }
            session.setAttribute("current_page", router.getCurrentPage());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
// 9:28