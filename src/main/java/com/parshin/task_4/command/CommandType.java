package com.parshin.task_4.command;

import com.parshin.task_4.command.impl.admin.AddUserCommand;
import com.parshin.task_4.command.impl.common.DefaultCommand;
import com.parshin.task_4.command.impl.common.LoginCommand;
import com.parshin.task_4.command.impl.common.LogoutCommand;
import com.parshin.task_4.command.impl.common.RegisterCommand;
import com.parshin.task_4.command.impl.topage.AdminPageCommand;
import com.parshin.task_4.command.impl.topage.RegistrationPageCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    DEFAULT(new DefaultCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    REGISTER(new RegisterCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
