package com.parshin.task_4.command.impl.common;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.entity.User;
import com.parshin.task_4.exception.CommandException;
import com.parshin.task_4.exception.ServiceException;
import com.parshin.task_4.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.parshin.task_4.command.AttributeName.*;

public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final String ERROR = "LoginCommand Service exception : ";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserServiceImpl userService = UserServiceImpl.getInstance();
        Router router = new Router();
        try {
            Optional<User> optionalUser = userService.authenticateUser(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute(USER, user);
                session.setAttribute(ACCESS_LEVEL_ATTRIBUTE, user.getUserAccessLevel());
                logger.log(Level.DEBUG, "attribute for user {} were set", user);
                router.setRouteType(Router.RouteType.REDIRECT);
                router.setCurrentPage(PagePath.MAIN_PAGE_PATH);
            } else {
                request.setAttribute(LOGIN_MESSAGE, "Incorrect login or password");
                router.setCurrentPage(PagePath.INDEX_PAGE_PATH);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"login command error. {}",e.getMessage());
            request.setAttribute(ERROR_MESSAGE, ERROR + e.getMessage());
            throw new CommandException(e);
        }
        return router;
    }
}
