package by.epam.trainig.controller;

import by.epam.trainig.controller.impl.PropertyContextImpl;

public interface PropertyContext {

    String get(String name);

    static PropertyContext getInstance() {
        return PropertyContextImpl.getInstance();
    }

}
