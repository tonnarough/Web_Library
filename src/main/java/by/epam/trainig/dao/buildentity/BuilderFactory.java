package by.epam.trainig.dao.buildentity;

import by.epam.trainig.dao.buildentity.impl.*;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.*;

public class BuilderFactory implements EntityBuilderFactory {

    private EntityBuilder<User> userBuilder;
    private EntityBuilder<UserDetail> userDetailBuilder;
    private EntityBuilder<Role> roleBuilder;
    private EntityBuilder<Subscription> subscriptionBuilder;
    private EntityBuilder<SubscriptionType> subscriptionTypeBuilder;
    private EntityBuilder<CreditCard> creditCardEntityBuilder;

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

        } else if (UserDetail.class.isAssignableFrom(type)) {
            if (userDetailBuilder == null) {
                userDetailBuilder = new UserDetailsBuilder();
            }
            builder = (EntityBuilder<T>) userDetailBuilder;

        } else if (Role.class.isAssignableFrom(type)) {
            if (roleBuilder == null) {
                roleBuilder = new RoleBuilder();
            }
            builder = (EntityBuilder<T>) roleBuilder;

        } else if (Subscription.class.isAssignableFrom(type)) {
            if (subscriptionBuilder == null) {
                subscriptionBuilder = new SubscriptionBuilder();
            }
            builder = (EntityBuilder<T>) subscriptionBuilder;
        } else if (SubscriptionType.class.isAssignableFrom(type)) {
            if (subscriptionTypeBuilder == null) {
                subscriptionTypeBuilder = new SubscriptionTypeBuilder();
            }
            builder = (EntityBuilder<T>) subscriptionTypeBuilder;
        } else if (CreditCard.class.isAssignableFrom(type)) {
            if (creditCardEntityBuilder == null) {
                creditCardEntityBuilder = new CreditCardBuilder();
            }
            builder = (EntityBuilder<T>) creditCardEntityBuilder;
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

