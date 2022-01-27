package by.epam.trainig.entity.book;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.Objects;

@Table(name = "publishing_houses")
public class PublishingHouse implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;

    public PublishingHouse() {
    }

    public PublishingHouse(int id, String title, int yearOfPublishing) {
        this.id = id;
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouse that = (PublishingHouse) o;
        return id == that.id && yearOfPublishing == that.yearOfPublishing && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, yearOfPublishing);
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }

}
