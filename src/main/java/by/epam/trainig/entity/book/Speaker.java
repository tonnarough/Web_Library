package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class Speaker implements Entity {
    private int speakerId;
    private String lastName;
    private String name;
    private String fatherName;

    public Speaker() {
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Speaker speaker = (Speaker) o;
        return speakerId == speaker.speakerId && Objects.equals(lastName, speaker.lastName) &&
                Objects.equals(name, speaker.name) && Objects.equals(fatherName, speaker.fatherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speakerId, lastName, name, fatherName);
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "speakerId=" + speakerId +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
