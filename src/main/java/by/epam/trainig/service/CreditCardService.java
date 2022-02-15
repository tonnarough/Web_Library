package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.service.impl.CreditCardServiceImpl;

public interface CreditCardService extends EntityService<CreditCard> {

    static CreditCardService getInstance(){
        return CreditCardServiceImpl.getInstance();
    }

}
