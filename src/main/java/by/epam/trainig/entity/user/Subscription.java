package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.sql.Date;
import java.util.Objects;

@Table(name = "subscriptions")
public class Subscription implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private User user;
    @Column(name = "subscription_type_id")
    private SubscriptionType subscriptionType;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    public Subscription() {
    }

    public Subscription(User user, SubscriptionType subscriptionType) {
        this.user = user;
        this.subscriptionType = subscriptionType;
    }

    public Subscription(User user, SubscriptionType subscriptionType, Date startDate, Date endDate) {
        this.user = user;
        this.subscriptionType = subscriptionType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Subscription(int id, User user, SubscriptionType subscriptionType, Date startDate, Date endDate) {
        this.id = id;
        this.user = user;
        this.subscriptionType = subscriptionType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
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
        return id == that.id && user.equals(that.user) && subscriptionType.equals(that.subscriptionType) && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, subscriptionType, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", subscriptionType=" + subscriptionType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
