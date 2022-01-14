package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.Date;
import java.util.Objects;

@Table(name = "subscriptions")
public class Subscription implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "subscription_type_id")
    private int subscriptionTypeId;
    @Column(name = "expired")
    private boolean expired;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    public Subscription() {
    }

    public Subscription(int id, int userId, int subscriptionTypeId,
                         boolean expired, Date startDate, Date endDate) {
        this.id = id;
        this.userId = userId;
        this.subscriptionTypeId = subscriptionTypeId;
        this.expired = expired;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(int subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id && userId == that.userId && subscriptionTypeId == that.subscriptionTypeId &&
                expired == that.expired && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, subscriptionTypeId, expired, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "id=" + id +
                ", userId=" + userId +
                ", subscriptionTypeId=" + subscriptionTypeId +
                ", expired=" + expired +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

