package by.epam.trainig.service;

import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.SubscriptionServiceImpl;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService extends EntityService<Subscription> {

    Optional<Subscription> findByUserId(Integer id);

    void create(Subscription entity, SubscriptionType subscriptionType);

    SubscriptionType findByType(String chosenType) throws ServiceException;

    List<SubscriptionType> findAllTypes() throws ServiceException;

    void update(User user, SubscriptionType subscriptionType);

    static SubscriptionServiceImpl getInstance(){
        return SubscriptionServiceImpl.INSTANCE;
    }

}
