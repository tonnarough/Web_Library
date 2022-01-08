package by.epam.trainig.dao.buildentity;

import by.epam.trainig.entity.Entity;

public interface EntityBuilderFactory {

    <T extends Entity> EntityBuilder<T> entityBuild(Class<T> type);

    static BuilderFactory getInstance() {
        return BuilderFactory.getInstance();
    }

}
