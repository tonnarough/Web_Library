package by.epam.trainig.dao.buildentity;

import by.epam.trainig.dao.buildentity.impl.UserBuilder;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.User;

public class BuilderFactory implements EntityBuilderFactory {

    private EntityBuilder<User> userBuilder;

    private BuilderFactory() {
    }

    @Override
    public <T extends Entity> EntityBuilder<T> entityBuild(Class<T> type) {
        EntityBuilder<T> builder = null;

        if (User.class.isAssignableFrom(type)) {
            if (userBuilder == null) {
                userBuilder = new UserBuilder();
            }
            builder = (EntityBuilder<T>) userBuilder;
        }
        return builder;
    }

    public static BuilderFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final BuilderFactory INSTANCE = new BuilderFactory();
    }
}
