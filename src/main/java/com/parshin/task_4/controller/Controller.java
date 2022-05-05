package com.parshin.task_4.controller;

import java.io.*;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.CommandType;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.exception.CommandException;
import com.parshin.task_4.pool.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        try {
            Command command = CommandType.define(commandStr);
            Router router = command.execute(request);
            switch (router.getRouteType()) {
                case FORWARD -> {
                    logger.log(Level.INFO, "Forward. To page :{}", router.getCurrentPage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getCurrentPage());
                    dispatcher.forward(request, response);
                }
                case REDIRECT -> {
                    logger.log(Level.INFO, "Redirect. To page :{}", router.getCurrentPage());
                    response.sendRedirect(router.getCurrentPage());
                }
            }
        } catch (CommandException e) {
            //response.sendError(500); //1
            //throw new ServletException(e); //2
            request.setAttribute("error_msg", e.getCause()); //3
            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response); //3
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    public void destroy() {
    }
}