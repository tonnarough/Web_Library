package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.BankAccountService;
import by.epam.trainig.service.SubscriptionService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

public enum SubscriptionCommand implements Command {
    INSTANCE(SubscriptionService.getInstance(), BankAccountService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";
    private static final String SUBSCRIPTION_PAGE = "page.subscription";

    private static final String DROP_DOWN_MENU_CHOSEN_ITEM = "subscriptionType";
    private static final String CREDIT_CARD_NUMBER = "credit_card_number";
    private static final String CARDHOLDER_NAME = "cardholder_name";
    private static final String CARD_EXPIRATION_DATE = "date";
    private static final String CVV = "cvv";
    private static final String BALANCE = "balance";

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";

    private static final String ERROR_PAGE = "go_to_error_page";

    private static final String ERROR_SUBSCRIPTION_ATTRIBUTE = "paymentError";
    private static final String ERROR_SUBSCRIPTION_MESSAGE = "Insufficient funds to pay";

    private final SubscriptionService subscriptionService;
    private final BankAccountService bankAccountService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    SubscriptionCommand(SubscriptionService subscriptionService, BankAccountService bankAccountService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.subscriptionService = subscriptionService;
        this.bankAccountService = bankAccountService;
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

        final Optional<Object> userFromSession = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final SubscriptionType subscriptionType = subscriptionService.findByType(chosenSubscriptionType);
        final Optional<CreditCard> creditCard = bankAccountService.findCreditCardBy(CREDIT_CARD_NUMBER, creditCardNumber);

        final User user;

        if (userFromSession.isPresent()) {

            if (userFromSession.get() instanceof User) {

                user = (User) userFromSession.get();

            } else {

                request.addAttributeToJsp(ERROR_SUBSCRIPTION_ATTRIBUTE, ERROR_SUBSCRIPTION_MESSAGE); //TODO attribute & message

                return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));
            }

        } else {

            request.addAttributeToJsp(ERROR_SUBSCRIPTION_ATTRIBUTE, ERROR_SUBSCRIPTION_MESSAGE); //TODO attribute & message

            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));
        }

        if (creditCard.isPresent()) {

            if (subscriptionType.getPrice().compareTo(creditCard.get().getBalance()) > 0) {

                request.addAttributeToJsp(ERROR_SUBSCRIPTION_ATTRIBUTE, ERROR_SUBSCRIPTION_MESSAGE);

                return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));
            }

            bankAccountService.updateCreditCard(BALANCE, creditCard.get().getBalance().subtract(subscriptionType.getPrice()), CREDIT_CARD_NUMBER, creditCardNumber);

            doesTheUserHaveASubscription(user, subscriptionType);

        } else {

            bankAccountService.create(
                    user,
                    new CreditCard(creditCardNumber,
                            cardHolderName,
                            cardExpirationDate,
                            cvv,
                            BigDecimal.valueOf(100).subtract(subscriptionType.getPrice()))
            );

            doesTheUserHaveASubscription(user, subscriptionType);
        }

        return requestFactory.createRedirectResponse(propertyContext.get(MAIN_AUTH_PAGE));
    }

    private void doesTheUserHaveASubscription(User user, SubscriptionType subscriptionType){

        if (subscriptionService.findByUserId(user.getId()).isPresent()) {

            subscriptionService.update(user, subscriptionType);

        } else {

            subscriptionService.create(new Subscription(user.getId(), subscriptionType.getId()), subscriptionType);

        }
    }
}
