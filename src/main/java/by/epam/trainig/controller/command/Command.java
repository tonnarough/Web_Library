package by.epam.trainig.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {

    CommandResponse execute(CommandRequest request) throws IOException, ServletException;

    static Command of(String name) {
        return null;
    }
}
