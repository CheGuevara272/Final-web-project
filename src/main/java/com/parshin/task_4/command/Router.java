package com.parshin.task_4.command;

public class Router {
    public enum RouteType {
        FORWARD,
        REDIRECT,
        ERROR
    }

    private String currentPage = PagePath.INDEX_PAGE_PATH;
    private RouteType routeType = RouteType.FORWARD;

    public Router() {
    }

    public Router(String currentPage) {
        this.currentPage = currentPage;
    }

    public Router(RouteType routeType) {
        this.routeType = routeType;
    }

    public Router(String currentPage, RouteType routeType) {
        this.currentPage = currentPage;
        this.routeType = routeType;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }
}
