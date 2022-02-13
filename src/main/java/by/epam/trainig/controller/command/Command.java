package by.epam.trainig.controller.command;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {

    CommandResponse execute(CommandRequest request) throws IOException, ServletException;

    static Command of(String name) {
        return CommandProvider.getCommand(name);
    }

    default String urlBuilder(String url, String parameter) {
        return String.format("%s?command=%s", url, parameter);
    }


}
