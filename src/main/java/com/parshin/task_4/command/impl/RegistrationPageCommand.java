package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PagePath.REGISTER_PAGE_PATH;
    }
}
