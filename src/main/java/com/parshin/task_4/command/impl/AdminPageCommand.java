package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AdminPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return "pages/admin_page.jsp";
    }
}
