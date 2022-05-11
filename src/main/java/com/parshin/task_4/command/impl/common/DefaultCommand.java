package com.parshin.task_4.command.impl.common;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX_PAGE_PATH);
    }
}
