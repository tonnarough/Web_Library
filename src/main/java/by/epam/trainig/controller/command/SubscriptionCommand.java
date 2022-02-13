package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ControllerException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.SubscriptionService;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

public enum SubscriptionCommand implements Command {
    INSTANCE(SubscriptionService.getInstance(), UserService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final Logger logger = LogManager.getLogger(SubscriptionCommand.class);

    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";
    private static final String SUBSCRIPTION_PAGE = "page.subscription";

    private static final String DROP_DOWN_MENU_CHOSEN_ITEM = "subscriptionType";
    private static final String CREDIT_CARD_NUMBER = "credit_card_number";
    private static final String CARDHOLDER_NAME = "cardholder_name";
    private static final String CARD_EXPIRATION_DATE = "date";
    private static final String CVV = "cvv";
    private static final String BALANCE = "balance";

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";

    private static final String ERROR_PAGE = "page.error";

    private static final String ERROR_PAYMENT_PASS_ATTRIBUTE = "paymentError";
    private static final String ERROR_PAYMENT_PASS_MESSAGE = "Insufficient funds to pay";

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    SubscriptionCommand(SubscriptionService subscriptionService, UserService userService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        final String chosenSubscriptionType = request.getParameter(DROP_DOWN_MENU_CHOSEN_ITEM);
        final String creditCardNumber = request.getParameter(CREDIT_CARD_NUMBER);
        final String cardHolderName = request.getParameter(CARDHOLDER_NAME);
        final Date cardExpirationDate = Date.valueOf(request.getParameter(CARD_EXPIRATION_DATE));
        final int cvv = Integer.parseInt(request.getParameter(CVV));

        final Optional<CreditCard> creditCard = userService.findCreditCardBy(CREDIT_CARD_NUMBER, creditCardNumber);

        final Optional<Object> userFromSession = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);

        final SubscriptionType subscriptionType;

        subscriptionType = subscriptionService.findByType(chosenSubscriptionType);

        final User user;

        if (userFromSession.isPresent() && userFromSession.get() instanceof User) {

            user = (User) userFromSession.get();

        } else {

            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));

        }

        if (creditCard.isPresent()) {

            try {

                updateCreditCard(subscriptionType, user, creditCard.get(), creditCardNumber);

            } catch (ControllerException e) {

                logger.error(e);
                request.addAttributeToJsp(ERROR_PAYMENT_PASS_ATTRIBUTE, ERROR_PAYMENT_PASS_MESSAGE);
                return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));
            }

        } else {

            try {

                createCreditCard(user, subscriptionType, creditCardNumber, cardHolderName, cardExpirationDate, cvv);

            } catch (ControllerException e) {

                logger.error(e);
                return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

            }

        }

        return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));
    }



    private void createCreditCard(User user, SubscriptionType subscriptionType, String creditCardNumber, String cardHolderName, Date cardExpirationDate, int cvv) throws ControllerException {

        try {

            userService.createCreditCard(
                    user,
                    new CreditCard(creditCardNumber,
                            cardHolderName,
                            cardExpirationDate,
                            cvv,
                            BigDecimal.valueOf(100).subtract(subscriptionType.getPrice()))
            );

            doesTheUserHaveASubscription(user, subscriptionType);

        } catch (ServiceException e) {

            logger.error("Failed with creating bank account for user", e);
            throw new ControllerException(e);

        }

    }

    private void updateCreditCard(SubscriptionType subscriptionType, User user, CreditCard creditCard, String creditCardNumber) throws ControllerException {

        if (subscriptionType.getPrice().compareTo(creditCard.getBalance()) > 0) {

            throw new ControllerException("Insufficient funds");

        }

        try {

            userService.updateCreditCard(BALANCE, creditCard.getBalance().subtract(subscriptionType.getPrice()), CREDIT_CARD_NUMBER, creditCardNumber);

        } catch (ServiceException e) {

            logger.error("Failed credit card updating", e);
            throw new ControllerException(e);

        }

        doesTheUserHaveASubscription(user, subscriptionType);

    }

    private void doesTheUserHaveASubscription(User user, SubscriptionType subscriptionType) throws ControllerException {

        if (subscriptionService.findByUserId(user.getId()).isPresent()) {

            try {

                subscriptionService.update(user, subscriptionType);

            } catch (ServiceException e) {

                logger.error("Failed subscription updating", e);
                throw new ControllerException(e);

            }

        } else {

            try {

                subscriptionService.create(new Subscription(user, subscriptionType));

            } catch (ServiceException e) {

                logger.error("Failed subscription creating", e);
                throw new ControllerException(e);

            }

        }
    }

}
