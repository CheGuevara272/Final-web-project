package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AdminPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.ADMIN_PAGE_PATH);
    }
}
