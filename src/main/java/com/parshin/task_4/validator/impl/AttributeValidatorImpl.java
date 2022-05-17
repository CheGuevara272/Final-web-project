package com.parshin.task_4.validator.impl;

import com.parshin.task_4.validator.AttributeValidator;

public class AttributeValidatorImpl implements AttributeValidator {
    private static final AttributeValidatorImpl instance = new AttributeValidatorImpl();
    public static final String NAME_AND_SURNAME_PATTERN = "^[A-Za-zА-Яа-я]{3,20}$";
    public static final String LOGIN_PATTERN = "^[A-Za-zА-Яа-я0-9_]{4,20}$";
    public static final String PASSWORD_PATTERN = "^[A-Za-zА-Яа-я0-9_!@#,\\.]{6,20}$";
    public static final String EMAIL_PATTERN = "^[^[\\d\\.]][A-Za-z\\.\\d]{1,30}@[a-z]{2,10}\\.([a-z]{2,4}|[a-z]{2,4}\\.[a-z]{2,4})$";
    public static final String PHONE_PATTERN = "^\\((025|029|044|033)\\)\\d{7}$";


    private AttributeValidatorImpl(){}

    public static AttributeValidatorImpl getInstance (){
        return instance;
    }

    @Override
    public boolean validateLogin(String login) {
        return (login != null && login.matches(LOGIN_PATTERN));
    }

    @Override
    public boolean validatePassword(String password) {
        return (password != null && password.matches(PASSWORD_PATTERN));
    }
}
