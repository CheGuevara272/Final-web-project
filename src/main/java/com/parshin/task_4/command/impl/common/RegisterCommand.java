package com.parshin.task_4.command.impl.common;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.exception.CommandException;
import com.parshin.task_4.exception.ServiceException;
import com.parshin.task_4.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.parshin.task_4.command.AttributeName.*;

public class RegisterCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> userMap = new HashMap();
        userMap.put(LOGIN_ATTRIBUTE, request.getParameter(LOGIN_ATTRIBUTE));
        userMap.put(PASSWORD_ATTRIBUTE, request.getParameter(PASSWORD_ATTRIBUTE));
        userMap.put(NAME_ATTRIBUTE, request.getParameter(NAME_ATTRIBUTE));
        userMap.put(SURNAME_ATTRIBUTE, request.getParameter(SURNAME_ATTRIBUTE));
        userMap.put(BIRTHDAY_ATTRIBUTE, request.getParameter(BIRTHDAY_ATTRIBUTE));
        userMap.put(PHONE_ATTRIBUTE, request.getParameter(PHONE_ATTRIBUTE));
        userMap.put(EMAIL_ATTRIBUTE, request.getParameter(EMAIL_ATTRIBUTE));

        UserServiceImpl userService = UserServiceImpl.getInstance();
        boolean result;
        try {
            result = userService.addUser(userMap);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Registration command error. {}", e.getMessage());
            throw new CommandException("Registration command error..", e);
        }

        Router router = new Router();
        if (result) {
            router.setCurrentPage(PagePath.INDEX_PAGE_PATH);
        } else {
            for (Map.Entry<String, String> entry : userMap.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            router.setCurrentPage(PagePath.REGISTER_PAGE_PATH);
        }
        return router;
    }
}
