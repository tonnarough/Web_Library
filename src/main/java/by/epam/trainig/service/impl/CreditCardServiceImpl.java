package by.epam.trainig.service.impl;

import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.service.CommonService;
import by.epam.trainig.service.CreditCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CreditCardServiceImpl extends CommonService<CreditCard> implements CreditCardService {

    private static final Logger logger = LogManager.getLogger(UserDetailServiceImpl.class);

    private final CreditCardDAO creditCardDAO;

    CreditCardServiceImpl(CreditCardDAO creditCardDAO) {
        super(creditCardDAO, logger);
        this.creditCardDAO = creditCardDAO;
    }

    public static CreditCardService getInstance() {
        return CreditCardServiceImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreditCardService INSTANCE = new CreditCardServiceImpl(
                CreditCardDAO.getInstance()
        );
    }

}
