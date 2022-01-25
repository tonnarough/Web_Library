package by.epam.trainig.controller.impl;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.controller.PropertyContext;

public final class PropertyContextImpl implements PropertyContext {

    private static final String PAGE_START_WITH_FOR_FORWARD = "page.";
    private static final String PAGE_START_WITH_FOR_REDIRECT = "go_to";
    private static final String URL = "controller?command=";

    private PropertyContextImpl() {
    }

    public static PropertyContextImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String get(String name) {
        if (name.startsWith(PAGE_START_WITH_FOR_FORWARD)) {
            return PagePath.of(name.substring(5)).getPath();
        } else if (name.startsWith(PAGE_START_WITH_FOR_REDIRECT)) {
            return URL + name;
        }
        return null;
        //TODO exception
    }

    private static class Holder {
        public static final PropertyContextImpl INSTANCE = new PropertyContextImpl();
    }

}

