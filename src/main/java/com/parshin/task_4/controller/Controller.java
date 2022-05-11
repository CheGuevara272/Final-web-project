package com.parshin.task_4.controller;

import java.io.*;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.CommandType;
import com.parshin.task_4.command.Router;
import com.parshin.task_4.exception.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.parshin.task_4.command.AttributeName.COMMAND;
import static com.parshin.task_4.command.AttributeName.ERROR_MESSAGE;

@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandName = request.getParameter(COMMAND);
        logger.log(Level.DEBUG, "controller get command : {}", commandName);
        try {
            Command command = CommandType.define(commandName);
            Router router = command.execute(request);
            switch (router.getRouteType()) {
                case FORWARD -> {
                    logger.log(Level.INFO, "forward type. To page :{}", router.getCurrentPage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher(router.getCurrentPage());
                    dispatcher.forward(request, response);
                }
                case REDIRECT -> {
                    logger.log(Level.INFO, "redirect type. To page :{}", router.getCurrentPage());
                    response.sendRedirect(router.getCurrentPage());
                }
            }
        } catch (CommandException e) {
            logger.log(Level.ERROR, "incorrect command : {}", e.getMessage());
            request.setAttribute(ERROR_MESSAGE, "incorrect command.");
            response.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    public void destroy() {
    }
}