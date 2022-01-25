package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.Objects;

@Table(name = "bank_account")
public class BankAccount implements Entity {

    @Column(name = "user_id")
    private int userId;
    @Column(name = "credit_card_id")
    private int creditCardId;

    public BankAccount(int userId, int creditCardId) {
        this.userId = userId;
        this.creditCardId = creditCardId;
    }

    public BankAccount() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return userId == that.userId && creditCardId == that.creditCardId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, creditCardId);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "userId=" + userId +
                ", creditCardId=" + creditCardId +
                '}';
    }
}
