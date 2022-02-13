package by.epam.trainig.entity.user;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "subscription_types")
public class SubscriptionType implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;

    public SubscriptionType() {
    }

    public SubscriptionType(int id) {
        this.id = id;
    }

    public SubscriptionType(int id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionType that = (SubscriptionType) o;
        return id == that.id && description.equals(that.description) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price);
    }

    @Override
    public String toString() {
        return "SubscribtionType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}