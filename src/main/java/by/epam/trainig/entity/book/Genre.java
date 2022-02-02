package by.epam.trainig.entity.book;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.List;
import java.util.Objects;

@Table(name = "genres")
public class Genre implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;

    @Column(name = "book_id")
    private List<Book> books;

    public Genre() {
    }

    public Genre(int id, String title) {
        this.id = id;
        this.title = title;
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
        Genre genre = (Genre) o;
        return id == genre.id && title.equals(genre.title) && books.equals(genre.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, books);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", books=" + books +
                '}';
    }
}
