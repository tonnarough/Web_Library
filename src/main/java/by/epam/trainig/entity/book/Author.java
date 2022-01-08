package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class Author implements Entity {
    private int authorId;
    private String name;
    private String lastName;
    private String fatherName;

    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author authors = (Author) o;
        return authorId == authors.authorId && name.equals(authors.name) &&
                lastName.equals(authors.lastName) && Objects.equals(fatherName, authors.fatherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, lastName, fatherName);
    }

    @Override
    public String toString() {
        return "Authors{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
