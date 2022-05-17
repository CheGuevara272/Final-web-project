package com.parshin.task_4.entity;

import java.util.Calendar;
import java.sql.Date;
import java.util.StringJoiner;

public class User extends AbstractEntity {
    private String login;
    private UserAccessLevel userAccessLevel;
    private String name;
    private String surname;
    private Date birthday;
    private String phone;
    private String email;

    public User(){
    }

    public User(String login,  UserAccessLevel userAccessLevel, String name,
                String surname, Date birthday, String phone, String email) {
        this.login = login;
        this.userAccessLevel = userAccessLevel;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public UserAccessLevel getUserAccessLevel() {
        return userAccessLevel;
    }

    public void setUserAccessLevel(UserAccessLevel userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        if (userAccessLevel != user.userAccessLevel) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!birthday.equals(user.birthday)) return false;
        if (!phone.equals(user.phone)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + userAccessLevel.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("login='" + login + "'")
                .add("userAccessLevel=" + userAccessLevel)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("birthday=" + birthday)
                .add("phone='" + phone + "'")
                .add("email='" + email + "'")
                .toString();
    }
}
