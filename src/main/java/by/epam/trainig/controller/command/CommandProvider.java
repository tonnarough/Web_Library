package by.epam.trainig.controller.command;

import java.util.HashMap;
import java.util.Map;

public enum CommandProvider {
    SIGN_IN(new LoginCommand(), "sign_in"),
    REGISTRATION(new RegistrationCommand(), "registration"),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand(), "GO_TO_LOGIN_PAGE"),
    GO_TO_REGISTRATION_PAGE(new GoToRegistrationCommand(), "GO_TO_REGISTRATION_PAGE"),
    GO_TO_MAIN_AUTH_PAGE(new GoToMainAuthPageCommand(), "go_to_main_auth_page"),
    DEFAULT(new GoToMainAuthPageCommand(), "go_to_main_auth_page");

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
