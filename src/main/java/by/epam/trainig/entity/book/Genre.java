package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class Genre implements Entity {
    private int genreId;
    private String title;

    public Genre() {
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreId == genre.genreId && title.equals(genre.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, title);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", title='" + title + '\'' +
                '}';
    }
}
