package by.epam.trainig.controller.command;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider{

    private final String SIGN_IN = "sign_in";
    private final String REGISTRATION = "registration";
    private final String GO_TO_LOGIN_PAGE = "GO_TO_LOGIN_PAGE";
    private final String GO_TO_REGISTRATION_PAGE = "GO_TO_REGISTRATION_PAGE";
    private final String GO_TO_MAIN_AUTH_PAGE = "go_to_main_auth_page";

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
    commands.put(SIGN_IN, new LoginCommand());
    commands.put(REGISTRATION, new RegistrationCommand());
    commands.put(GO_TO_REGISTRATION_PAGE, new GoToRegistrationCommand());
    commands.put(GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
    commands.put(GO_TO_MAIN_AUTH_PAGE, new GoToMainAuthPageCommand());
    }

    public Command getCommand(String commandName){
        Command command = commands.get(commandName);
        return command;
    }
}
