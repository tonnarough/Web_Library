package by.epam.trainig.controller;

public interface PropertyContext {

    String get(String name);

    static PropertyContext getInstance() {
        return PropertyContextImpl.getInstance();
    }

}
