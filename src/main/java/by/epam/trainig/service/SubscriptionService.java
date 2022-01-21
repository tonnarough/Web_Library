package by.epam.trainig.service;

import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.service.impl.SubscriptionServiceImpl;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService extends EntityService<Subscription> {

    Optional<Subscription> findByUserId(Integer id);

    public List<SubscriptionType> findAllTypes();

    static SubscriptionServiceImpl getInstance(){
        return SubscriptionServiceImpl.INSTANCE;
    }

}
