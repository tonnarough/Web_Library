package by.epam.trainig.entity.book;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.Objects;

@Table(name = "books")
public class Book implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "titile")
    private String titile;
    @Column(name = "description")
    private String description;
    @Column(name = "age_limit")
    private String ageLimit;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "file")
    private String file;
    @Column(name = "picture")
    private String picture;

    public Book() {
    }

    public Book(int id, String titile, String description,
                String ageLimit, int numberOfPages, String file, String picture) {
        this.id = id;
        this.titile = titile;
        this.description = description;
        this.ageLimit = ageLimit;
        this.numberOfPages = numberOfPages;
        this.file = file;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
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
        return id == book.id && numberOfPages == book.numberOfPages && titile.equals(book.titile) && description.equals(book.description) && ageLimit.equals(book.ageLimit) && file.equals(book.file) && picture.equals(book.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titile, description, ageLimit, numberOfPages, file, picture);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titile='" + titile + '\'' +
                ", description='" + description + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", file='" + file + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

}
