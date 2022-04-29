package com.parshin.task_4.controller;

import java.io.*;

import com.parshin.task_4.command.Command;
import com.parshin.task_4.command.CommandType;
import com.parshin.task_4.exception.CommandException;
import com.parshin.task_4.pool.ConnectionPool;
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
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "-------> Servlet initialized: {}", this.getServletInfo());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
//        String strNum = request.getParameter("num");
//        int resNum = 2 * Integer.parseInt(strNum);
//        request.setAttribute("result", resNum);
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
            //response.sendRedirect(page);
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
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "-------> Servlet destroyed: {}", this.getServletName());
    }
}