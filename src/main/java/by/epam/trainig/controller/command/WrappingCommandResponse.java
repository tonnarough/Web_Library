package by.epam.trainig.controller.command;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import javax.servlet.http.Cookie;
import java.io.FileInputStream;
import java.util.Objects;

public class WrappingCommandResponse implements CommandResponse {

    private final boolean redirect;
    private final String path;
    private final Cookie cookie;
    private final S3ObjectInputStream inputStream;

    public WrappingCommandResponse(String path) {
        this(false, path);
    }

    public WrappingCommandResponse(boolean redirect, String path) {
        this(redirect, path, null);
    }

    public WrappingCommandResponse(boolean redirect, String path, Cookie cookie) {
        this(redirect, path, cookie, null);
    }

    public WrappingCommandResponse(boolean redirect, String path, Cookie cookie, S3ObjectInputStream inputStream) {
        this.redirect = redirect;
        this.path = path;
        this.cookie = cookie;
        if(cookie != null){
            cookie.setMaxAge(24 * 60 * 60);
        }
        this.inputStream = inputStream;
    }

    @Override
    public Cookie getCookie() {
        return cookie;
    }

    @Override
    public S3ObjectInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrappingCommandResponse that = (WrappingCommandResponse) o;
        return redirect == that.redirect && path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirect, path);
    }

    @Override
    public String toString() {
        return "WrappingCommandResponse{" +
                "redirect=" + redirect +
                ", path='" + path + '\'' +
                '}';
    }
}
