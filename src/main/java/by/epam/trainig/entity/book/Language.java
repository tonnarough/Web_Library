package by.epam.trainig.entity.book;

import by.epam.trainig.entity.Entity;

import java.util.Objects;

public class Language implements Entity {
    private int languageId;
    private String language;

    public Language() {
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language1 = (Language) o;
        return languageId == language1.languageId && language.equals(language1.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, language);
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", language='" + language + '\'' +
                '}';
    }
}
