package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.annotation.Table;

import java.sql.Date;
import java.util.Objects;

@Table(name = "users")
public class User implements Entity {

    @Column(name = "usersId")
    private int usersId;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String name;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "passport")
    private String passport;
    @Column(name = "birthday")
    private Date birthday;

    public User(int usersId, String login, String password,
                String email, String lastName, String name, String fatherName,
                String gender, String mobile, String passport, Date birthday) {
        this.usersId = usersId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.fatherName = fatherName;
        this.gender = gender;
        this.mobile = mobile;
        this.passport = passport;
        this.birthday = birthday;
    }

    public User() {
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return usersId == users.usersId && login.equals(users.login) &&
                password.equals(users.password) && email.equals(users.email) &&
                lastName.equals(users.lastName) && name.equals(users.name) &&
                fatherName.equals(users.fatherName) && gender.equals(users.gender) &&
                mobile.equals(users.mobile) && passport.equals(users.passport) &&
                birthday.equals(users.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, login, password, email, lastName, name,
                fatherName, gender, mobile, passport, birthday);
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", passport='" + passport + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
