package by.epam.trainig.entity.book;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.Entity;

import java.util.List;
import java.util.Objects;

@Table(name = "books")
public class Book implements Entity {

    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
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
    @Column(name = "author_id")
    private List<Author> authors;
    @Column(name = "genre_id")
    private List<Genre> genres;
    @Column(name = "publishing_house_id")
    private List<PublishingHouse> publishingHouses;

    public Book() {
    }

    public Book(int id, String title, String description,
                String ageLimit, int numberOfPages, String file, String picture) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setAuthor(Author author) {
        authors.add(author);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenre(Genre genre) {
        genres.add(genre);
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<PublishingHouse> getPublishingHouses() {
        return publishingHouses;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        publishingHouses.add(publishingHouse);
    }

    public void setPublishingHouses(List<PublishingHouse> publishingHouses) {
        this.publishingHouses = publishingHouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && numberOfPages == book.numberOfPages && title.equals(book.title) && description.equals(book.description) &&
                ageLimit.equals(book.ageLimit) && file.equals(book.file) && picture.equals(book.picture) && authors.equals(book.authors) &&
                genres.equals(book.genres) && publishingHouses.equals(book.publishingHouses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, ageLimit, numberOfPages, file, picture, authors, genres, publishingHouses);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", file='" + file + '\'' +
                ", picture='" + picture + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", publishingHouses=" + publishingHouses +
                '}';
    }
}
