package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class Book implements Entity {
    private int bookId;
    private String title;
    private String theYearOfPublishing;
    private String file;

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheYearOfPublishing() {
        return theYearOfPublishing;
    }

    public void setTheYearOfPublishing(String theYearOfPublishing) {
        this.theYearOfPublishing = theYearOfPublishing;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && title.equals(book.title) && theYearOfPublishing.equals(book.theYearOfPublishing) && file.equals(book.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, theYearOfPublishing, file);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", theYearOfPublishing='" + theYearOfPublishing + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
