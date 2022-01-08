package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class ChosenBook implements Entity {
    private int chosenBookId;
    private String bookType;

    public ChosenBook() {
    }

    public int getChosenBookId() {
        return chosenBookId;
    }

    public void setChosenBookId(int chosenBookId) {
        this.chosenBookId = chosenBookId;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChosenBook that = (ChosenBook) o;
        return chosenBookId == that.chosenBookId && bookType.equals(that.bookType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chosenBookId, bookType);
    }

    @Override
    public String toString() {
        return "ChosenBook{" +
                "chosenBookId=" + chosenBookId +
                ", bookType='" + bookType + '\'' +
                '}';
    }
}
