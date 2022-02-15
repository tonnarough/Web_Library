package by.epam.trainig.entity.book;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.List;
import java.util.Objects;

@Table(name = "publishing_houses")
public class PublishingHouse implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;
    @Column(name = "mtm_book_id")
    private List<Book> books;

    public PublishingHouse() {
    }

    public PublishingHouse(String title, int yearOfPublishing) {
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBook(Book book) {
        books.add(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouse that = (PublishingHouse) o;
        return id == that.id && yearOfPublishing == that.yearOfPublishing && title.equals(that.title) && books.equals(that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, yearOfPublishing, books);
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", books=" + books +
                '}';
    }

}
