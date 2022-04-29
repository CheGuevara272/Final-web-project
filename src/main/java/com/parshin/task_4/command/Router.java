package com.parshin.task_4.command;

public class Router {
    private String page = "index.jsp";
    private RouteType routeType = RouteType.FORWARD;
    enum RouteType {
        FORWARD, REDIRECT;
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, RouteType routeType) {
        this.page = page;
        this.routeType = routeType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRouteType() {
        this.routeType = RouteType.REDIRECT;
    }
}
