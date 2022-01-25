package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.service.BankAccountService;
import by.epam.trainig.service.SubscriptionService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum SubscriptionCommand implements Command {
    INSTANCE(SubscriptionService.getInstance(), BankAccountService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";
    private static final String SUBSCRIPTION_PAGE = "page.subscription";

    private static final String DROP_DOWN_MENU_CHOSEN_ITEM = "subscriptionType";
    private static final String CREDIT_CARD_NUMBER = "credit_card_number";
    private static final String CARDHOLDER_NAME = "cardholder_name";
    private static final String CARD_EXPIRATION_DATE ="date";
    private static final String CVV = "cvv";
    private static final String BALANCE = "balance";

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

        final List<SubscriptionType> subscriptionTypes = subscriptionService.findAllTypes()
                .stream()
                .filter(subType -> (subType.getDescription() + ": " + subType.getPrice()).equals(chosenSubscriptionType))
                .collect(Collectors.toList());

        final SubscriptionType subscriptionType = subscriptionTypes.get(0);
        final Optional<CreditCard> creditCard = bankAccountService.findBy(CREDIT_CARD_NUMBER, creditCardNumber);

        if (creditCard.isPresent()) {

            if(subscriptionType.getPrice().equals(creditCard.get().getBalance())){
                request.addAttributeToJsp(ERROR_SUBSCRIPTION_ATTRIBUTE, ERROR_SUBSCRIPTION_MESSAGE);
                return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));
            }

            bankAccountService.update(BALANCE, creditCard.get().getBalance().subtract(subscriptionType.getPrice()), CREDIT_CARD_NUMBER, creditCardNumber);
        } else {

            bankAccountService.create(new CreditCard(creditCardNumber, cardHolderName, cardExpirationDate, cvv));

            final Optional<CreditCard> newCreditCard = bankAccountService.findBy(CREDIT_CARD_NUMBER, creditCardNumber);
            //TODO: verifier
            bankAccountService.update(BALANCE, newCreditCard.get().getBalance().subtract(subscriptionType.getPrice()), CREDIT_CARD_NUMBER, creditCardNumber);
        }

        return requestFactory.createRedirectResponse(propertyContext.get(MAIN_AUTH_PAGE));
    }
}
