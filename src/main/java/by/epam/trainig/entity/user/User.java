package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "users")
public class User implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "role_id")
    private Role role;
    @Column(name = "user_details_id")
    private UserDetail userDetail;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "mtm_credit_card_id")
    private List<CreditCard> creditCards = new ArrayList<>();

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id, Role role, UserDetail userDetail, String login, String password) {
        this.id = id;
        this.role = role;
        this.userDetail = userDetail;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
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

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && role.equals(user.role) &&
                userDetail.equals(user.userDetail) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                creditCards.equals(user.creditCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, userDetail, login, password, creditCards);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", userDetail=" + userDetail +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", creditCards=" + creditCards +
                '}';
    }

}
