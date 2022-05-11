package com.parshin.task_4.command.impl.common;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.PagePath;
import com.parshin.task_4.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setCurrentPage(PagePath.INDEX_PAGE_PATH);
        return router;
    }
}
