package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.annotation.Table;

import java.util.Objects;

@Table(name = "users")
public class User implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "user_details_id")
    private int userDetailsId;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(int id, int roleId, int userDetailsId, String login,
                String password) {
        this.id = id;
        this.roleId = roleId;
        this.userDetailsId = userDetailsId;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(int userDetailsId) {
        this.userDetailsId = userDetailsId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId &&
                userDetailsId == user.userDetailsId && login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, userDetailsId, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userDetailsId=" + userDetailsId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
