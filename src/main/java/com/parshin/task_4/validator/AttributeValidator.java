package com.parshin.task_4.validator;

public interface AttributeValidator {
    boolean validateLogin(String login);
    boolean validatePassword(String password);
}
