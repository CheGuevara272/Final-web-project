package com.parshin.task_4.command.impl;

import com.parshin.task_4.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagePath.INDEX_PAGE_PATH;
    }
}
