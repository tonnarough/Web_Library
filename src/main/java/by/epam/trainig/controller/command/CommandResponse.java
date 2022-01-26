package by.epam.trainig.controller.command;

import javax.servlet.http.Cookie;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();

    Cookie getCookie();

}
