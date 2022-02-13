package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "credit_cards")
public class CreditCard implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name = "card_expiration_date")
    private Date cardExpirationDate;
    @Column(name = "cvv")
    private int CVV;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "mtm_user_id")
    private List<User> users = new ArrayList<>();

    public CreditCard() {
    }

    public CreditCard(String creditCardNumber, String cardholderName, Date cardExpirationDate, int CVV, BigDecimal balance) {
        this.creditCardNumber = creditCardNumber;
        this.cardholderName = cardholderName;
        this.cardExpirationDate = cardExpirationDate;
        this.CVV = CVV;
        this.balance = balance;
    }

    public CreditCard(int id, String creditCardNumber, String cardholderName, Date cardExpirationDate, int CVV, BigDecimal balance) {
        this.id = id;
        this.creditCardNumber = creditCardNumber;
        this.cardholderName = cardholderName;
        this.cardExpirationDate = cardExpirationDate;
        this.CVV = CVV;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public Date getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(Date cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUser(User user) {
        this.users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id && CVV == that.CVV && creditCardNumber.equals(that.creditCardNumber) && cardholderName.equals(that.cardholderName) && cardExpirationDate.equals(that.cardExpirationDate) && balance.equals(that.balance) && users.equals(that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditCardNumber, cardholderName, cardExpirationDate, CVV, balance, users);
    }
}
