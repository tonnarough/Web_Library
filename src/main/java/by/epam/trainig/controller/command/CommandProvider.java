package by.epam.trainig.controller.command;

public enum CommandProvider {
    SIGN_IN(LoginCommand.INSTANCE, "sign_in"),
    REGISTRATION(RegistrationCommand.INSTANCE, "registration"),
    SUBSCRIPTION(SubscriptionCommand.INSTANCE, "subscription"),
    LOGOUT(LogoutCommand.INSTANCE, "logout"),
    SET_LOCALE_COMMAND(SetLocaleCommand.INSTANCE, "set_locale"),
    GO_TO_BOOK_DETAIL_PAGE(GoToSelectedBookCommand.INSTANCE, "go_to_book_detail_page"),
    SELECTED_BOOK_COMMAND(DownloadBookCommand.INSTANCE, "download"),
    DELETE_BOOK(DeleteBookCommand.INSTANCE, "delete_book"),
    UPDATE_BOOK(UpdateBookCommand.INSTANCE, "update_book"),
    ADDING_BOOK(AddingBookCommand.INSTANCE, "book_adding"),
    GO_TO_UPDATE_BOOK_PAGE(GoToUpdateBookPageCommand.INSTANCE, "go_to_update_book_page"),
    GO_TO_ADDING_BOOK_PAGE(GoToAddingBookPageCommand.INSTANCE, "go_to_adding_book_page"),
    SEARCH_BOOK_COMMAND(GoSearchBookCommand.INSTANCE, "search_book"),
    GO_TO_LOGIN_PAGE(GoToLoginPageCommand.INSTANCE, "go_to_login_page"),
    GO_TO_MAIN_UNAUTH_PAGE(GoToMainUnauthPageCommand.INSTANCE, "go_to_main_unauth_page"),
    GO_TO_REGISTRATION_PAGE(GoToRegistrationCommand.INSTANCE, "go_to_registration_page"),
    GO_TO_MAIN_AUTH_PAGE(GoToMainAuthPageCommand.INSTANCE, "go_to_main_auth_page"),
    GO_TO_SUBSCRIPTION_PAGE(GoToSubscriptionPageCommand.INSTANCE, "go_to_subscription_page"),
    GO_TO_INDEX_PAGE(GoToIndexPageCommand.INSTANCE, "go_to_index_page"),
    DEFAULT(GoToMainAuthPageCommand.INSTANCE, "go_to_main_auth_page");

    private final String path;
    private final Command command;

    CommandProvider(Command command, String path) {
        this.command = command;
        this.path = path;
    }

    public static Command getCommand(String commandName) {
        for(CommandProvider constant : values()){
            if(constant.path.equalsIgnoreCase(commandName)){
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
