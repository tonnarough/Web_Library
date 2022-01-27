package by.epam.trainig.entity.book;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.Objects;

@Table(name = "books")
public class Book implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "file")
    private String file;
    @Column(name = "picture")
    private String picture;

    public Book() {
    }

    public Book(int id, String title, String file, String picture) {
        this.id = id;
        this.title = title;
        this.file = file;
        this.picture = picture;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && title.equals(book.title) && file.equals(book.file) && picture.equals(book.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, file, picture);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", file='" + file + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

}
