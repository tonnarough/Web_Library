package by.epam.trainig.service;

import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.SubscriptionServiceImpl;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService extends EntityService<Subscription> {

    List<SubscriptionType> findAllTypes();

    SubscriptionType findByType(String chosenType);

    void update(User user, SubscriptionType subscriptionType) throws ServiceException;

    Optional<Subscription> findByUserId(Integer id);

    static SubscriptionService getInstance(){
        return SubscriptionServiceImpl.getInstance();
    }

}
