package com.parshin.task_4.command;

import com.parshin.task_4.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    DEFAULT(new DefaultCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    ADMIN_PAGE(new AdminPageCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current =  CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
