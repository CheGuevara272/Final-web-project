package com.parshin.task_4.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class User extends AbstractEntity {
    private int userId;
    private String login;
    private String password;
    private UserAccessLevel userAccessLevel;
    private String name;
    private String surname;
    private String birthday;
    private String phone;
    private String email;

    private User() {
    }

    public User(String login, String password, UserAccessLevel userAccessLevel,
                String name, String surname, String birthday, String phone, String email) {
        //this.userId = userId;
        this.login = login;
        this.password = password;
        this.userAccessLevel = userAccessLevel;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public int getUserId() {
        return userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
        return userId == user.userId && userAccessLevel == user.userAccessLevel && login.equals(user.login) && surname.equals(user.surname) && name.equals(user.name) && password.equals(user.password) && email.equals(user.email) && birthday.equals(user.birthday) && phone.equals(user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userAccessLevel, login, surname, name, password, email, birthday, phone);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("userAccessLevel=" + userAccessLevel)
                .add("login='" + login + "'")
                .add("lastName='" + surname + "'")
                .add("name='" + name + "'")
                .add("password='" + password + "'")
                .add("Email='" + email + "'")
                .add("birthday='" + birthday + "'")
                .add("phoneNumber='" + phone + "'")
                .toString();
    }
}
