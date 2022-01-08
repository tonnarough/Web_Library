package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class PublishingHouse implements Entity {
    private int publishingHouseId;
    private String title;

    public PublishingHouse() {
    }

    public int getPublishingHouseId() {
        return publishingHouseId;
    }

    public void setPublishingHouseId(int publishingHouseId) {
        this.publishingHouseId = publishingHouseId;
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
        PublishingHouse that = (PublishingHouse) o;
        return publishingHouseId == that.publishingHouseId && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publishingHouseId, title);
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "publishingHouseId=" + publishingHouseId +
                ", title='" + title + '\'' +
                '}';
    }
}
